#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>

#define WRITE_REGISTER(cpu,reg,val)\
if((reg) != 0)(*cpu).gprs.R[(reg)]=(val)
#define INSTRUCTION_MEMORY_SIZE 1024
#define DATA_MEMORY_SIZE 2048
#define NUM_GPRS 64

typedef struct {
    char *name;
    int opcode;
    int num_operands;
}OpcodeInfo;

OpcodeInfo opcode_table[]={
    {"ADD",  0, 3},
    {"SUB",  1, 3},
    {"MUL",  2, 3},
    {"MOVI", 3, 2},
    {"BEQZ", 4, 2},
    {"ANDI", 5, 2},
    {"EOR",  6, 2},
    {"BR",   7, 2},
    {"SAL",  8, 2},
    {"SAR",  9, 2},
    {"LDR", 10, 2},
    {"STR", 11, 2},
};

int opcode_table_size = sizeof(opcode_table)/sizeof(OpcodeInfo);


typedef struct {
    uint16_t memory[INSTRUCTION_MEMORY_SIZE];
}InstructionMemory;


typedef struct{
    uint8_t memory[DATA_MEMORY_SIZE];
}DataMemory;


typedef struct{
    uint8_t R[NUM_GPRS];
}GPRs;


typedef struct 
{
    uint8_t C : 1; 
    uint8_t V : 1; 
    uint8_t N : 1; 
    uint8_t S : 1; 
    uint8_t Z : 1; 
    uint8_t reserved : 3; 
}StatusRegister;


typedef struct{
    uint16_t PC;
}ProgramCounter;


typedef struct {
    InstructionMemory imemory; 
    DataMemory dmemory;
    GPRs gprs;
    StatusRegister sregister;
    ProgramCounter pc;
}HarvardCPU;


typedef struct {
    uint16_t instr;
    int valid;
}PipelineStage;


int parse_register(const char *reg_str){
    if(reg_str[0] == 'R' ){
        return atoi(reg_str + 1);
    }
    return -1;
}

int get_opcode(const char *name, int *num_operands){
    for(int i =0;i<opcode_table_size;i++){
        if(strcmp(name,opcode_table[i].name)==0){
            *num_operands = opcode_table[i].num_operands;
            return opcode_table[i].opcode;
        }
    }
    return -1;
}


int parse_immediate(const char *imm_str){
    return atoi(imm_str);
}

int parse_operands(char *operand_str, char operands[][10], int max_operands){
    int count = 0 ;
    char *token = strtok(operand_str, ",");
    while(token != NULL && count < max_operands){
        while(*token == ' ')
        token++;
        strncpy(operands[count],token,9);
        operands[count][9] = '\0';
        count++;
        token = strtok(NULL,",");
    }
    return count;
}

void initializeCPU (HarvardCPU *cpu){
    memset(cpu,0,sizeof(HarvardCPU));
    (*cpu).pc.PC=0;
}

uint16_t fetchInstruction(HarvardCPU *cpu){
    return (*cpu).imemory.memory[(*cpu).pc.PC];
}


void updateFlag(HarvardCPU *cpu, uint8_t result, uint8_t op1, uint8_t op2, char operation){

    uint16_t full_result = 0;
    switch (operation)
    {
    case '+':full_result = (uint16_t)op1 + (uint16_t)op2; break;
    case '-':full_result=(uint16_t)op1 - (uint16_t)op2;break; 
    default:full_result=result;break;
    }

    (*cpu).sregister.C=(full_result &0b000100000000)?1:0;

    
    int8_t s_op1=(int8_t)op1;
    int8_t s_op2=(int8_t)op2;
    int8_t s_res=(int8_t)result;

    if(operation=='+'){
        (*cpu).sregister.V=((s_op1 > 0 && s_op2 > 0 && s_res < 0)||(s_op1 < 0 && s_op2 < 0 && s_res > 0))? 1 : 0;
    }
    else if(operation=='-'){
        (*cpu).sregister.V=((s_op1 < 0 && s_op2 < 0 && s_res > 0)||(s_op1 > 0 && s_op2 > 0 && s_res < 0))? 1 : 0;
    }
    else{
        (*cpu).sregister.V=0;
    }

    
    (*cpu).sregister.N=(result &0b10000000)?1:0;

    
    (*cpu).sregister.S = (*cpu).sregister.N^(*cpu).sregister.V;

  
    (*cpu).sregister.Z=(result==0)?1:0;

    
    *((uint8_t*)&(*cpu).sregister) &=0b00011111;
}

void executeInstruction(HarvardCPU *cpu,uint16_t instruction){
    uint8_t opcode=(instruction >> 12)& 0b00001111;
    uint8_t R1=(instruction >> 6) & 0x3F;
    uint8_t R2 = instruction & 0x3F;
    uint8_t IMM = instruction & 0x3F;

   printf("opcode %d R1 %d R2 %d \n", opcode, R1, R2);
        switch (opcode)
        {
        case 0:{ //ADD
            uint8_t op1 = (*cpu).gprs.R[R1];
            uint8_t op2 = (*cpu).gprs.R[R2];
            uint8_t result = op1 + op2;
            updateFlag(cpu,result,op1,op2,'+');
            (*cpu).gprs.R[R1] = result;
            break;
        }
        case 1:{ //SUB
            uint8_t op1 = (*cpu).gprs.R[R1];
            uint8_t op2 = (*cpu).gprs.R[R2];
            uint8_t result = op1 - op2;
            (*cpu).gprs.R[R1] = result;
            updateFlag(cpu,result,op1,op2,'-');
            break;
        }
        case 2:{ //MUL
            uint8_t op1 = (*cpu).gprs.R[R1];
            uint8_t op2 = (*cpu).gprs.R[R2];
            uint8_t result = op1 * op2;
            (*cpu).gprs.R[R1] = result;
            updateFlag(cpu,result,op1,op2,'*');
            break;
        } 
        case 3:{ //MOVI
            (*cpu).gprs.R[R1]=IMM;
            updateFlag(cpu,IMM,0,IMM,'+');
            break;
        }
        case 4:{ //BEQZ
            if((*cpu).gprs.R[R1]==0){
                (*cpu).pc.PC += IMM + 1;
            }
            break;
        }
        case 5:{ //ANDI
            uint8_t op1= (*cpu).gprs.R[R1];
            uint8_t result = op1 & IMM;
            (*cpu).gprs.R[R1]=result;
            updateFlag(cpu,result,op1,IMM, '&');
            break;
        }
        case 6:{ //EOR
            uint8_t op1 =(*cpu).gprs.R[R1];
            uint8_t result = op1 ^ IMM;
            (*cpu).gprs.R[R1]=result;
            updateFlag(cpu,result,op1,IMM,'^');
            break;
        }
        case 7:{ //BR
            (*cpu).pc.PC=((uint16_t)(*cpu).gprs.R[R1] << 8) | IMM;
            break;
        }
        case 8:{ //SAL
            uint8_t op1= (*cpu).gprs.R[R1];
            uint8_t result = op1 << IMM;
            (*cpu).gprs.R[R1]=result;
            updateFlag(cpu,result,op1,IMM, '<');
            break;
        }
        case 9:{ //SAR
            uint8_t op1= (*cpu).gprs.R[R1];
            uint8_t result = op1 >> IMM;
            (*cpu).gprs.R[R1]=result;
            updateFlag(cpu,result,op1,IMM, '>');
            break;
        }
        case 10:{ //LDR
            WRITE_REGISTER(cpu,R1,(*cpu).gprs.R[R1]=(*cpu).dmemory.memory[IMM]);
            break;
        }  
        case 11:{ //STR
            (*cpu).dmemory.memory[IMM]=(*cpu).gprs.R[R1];
            break;
        }
        default:
            break;
        }
    }



void runPipeline(HarvardCPU *cpu, int instructionCount){
    PipelineStage IF= {0,0}, ID={0,0}, EX={0,0};
    int cycle = 1;
    int pc_limit=(*cpu).pc.PC + instructionCount;
    int flush = 0;

    printf("-------------> %d \n", pc_limit);
    while((*cpu).pc.PC<pc_limit || ID.valid || EX.valid){
        printf("Clock Cycle %d:\n", cycle);

        //Execute
        if(EX.valid){
            printf("[EX ] Executing : %d\n", EX.instr);
            executeInstruction(cpu,EX.instr);

        }

        //Decode
        if(ID.valid){
            printf("[ID ] Decoding Instruction: %d\n", ID.instr);
        }

        //Fetch
        if((*cpu).pc.PC <pc_limit){
            IF.instr = fetchInstruction(cpu);
            IF.valid = 1;
            printf("[IF ] Fetching Instruction: %d\n", IF.instr);
            (*cpu).pc.PC++;
        }
        else{
            IF.valid=0;
        }

        if(flush){
            EX.valid = 0;
            ID.valid = 0;
            IF.valid = 0;
            flush = 0;
        }
        else{
        EX = ID;
        ID = IF;
        IF.valid = 0;


        }
        printf(" Register Values:\n");
        for(int i=0; i<NUM_GPRS; i++){
            printf("R%-2d: %4d  ", i , (*cpu).gprs.R[i]);
            if ((i + 1) % 8 == 0) printf("\n"); 
        }
    
        printf("SREG: C=%d V=%d N=%d S=%d Z=%d\n",(*cpu).sregister.C,(*cpu).sregister.V,(*cpu).sregister.N,(*cpu).sregister.S,(*cpu).sregister.Z);
    
        printf("PC: %d\n\n", (*cpu).pc.PC);
    
        // printf("Instruction Memory: \n");
        // for(int i=0;i<INSTRUCTION_MEMORY_SIZE;i++){
        //     if (cpu->imemory.memory[i] != 0)
        //     printf("IMEM[%03d] = %d\n", i, cpu->imemory.memory[i]);
        // }
    
        // printf("\nData Memory (non-zero only):\n");
        // for(int i=0;i<DATA_MEMORY_SIZE; i++){
        //     if((*cpu).dmemory.memory[i]!=0){
        //         printf("DMEM[%04d] = %d\n",i,(*cpu).dmemory.memory[i]);
        //     }
        // }
        
        cycle++;
        printf("\n");


    }



}


  
  void LoadFromFile( HarvardCPU *cpu,int *instructionCount) {
    FILE *file = fopen("program1.txt", "r");
    if (!file) {
        perror("Failed to open instruction file");
        exit(EXIT_FAILURE);
    }

    char line[100];
    int instr_index = 0;
    while (fgets(line, sizeof(line), file) && instr_index < INSTRUCTION_MEMORY_SIZE) {
        char *comment = strchr(line, '#');
        if (comment) *comment = '\0';

        char opcode_str[10];
        char operand_str[50];
        if (sscanf(line, "%s %[^\n]", opcode_str, operand_str) != 2) continue;

        int num_operands;
        int opcode = get_opcode(opcode_str, &num_operands);
        //if (opcode < 0) continue;

        char operands[3][10] = {0};
        int parsed_count = parse_operands(operand_str, operands, 3);

       // if (parsed_count != num_operands) continue;

        uint16_t instruction = 0;
        instruction |= (opcode & 0x0F) << 12;

        int r1 = 0, r2 = 0, imm = 0;
        instruction |= (opcode & 0x0F) << 12;

        if (opcode == 3 || opcode == 4 || opcode == 5 || opcode == 6 || opcode == 8 || opcode == 9 || opcode == 10 || opcode == 11) {
            r1 = parse_register(operands[0]);
            imm = parse_immediate(operands[1]);
            instruction |= (r1 & 0x3F) << 6;
            instruction |= (imm & 0x3F);
        } else if (opcode == 0 || opcode == 1 || opcode == 2) {
            r1 = parse_register(operands[0]);
            r2 = parse_register(operands[1]);
            instruction |= (r1 & 0x3F) << 6;
            instruction |= (r2 & 0x3F);
        } else if (opcode == 7) {
            r1 = parse_register(operands[0]);
            instruction |= (r1 & 0x3F) << 6;
            instruction |= 0; // no operand 2
        }
        

        cpu->imemory.memory[instr_index] = instruction;
        instr_index++;
    }

    fclose(file);
    
    *instructionCount = instr_index;
}


int main() {
    HarvardCPU cpu;
    initializeCPU(&cpu);

    int instructionCount = 0;
    LoadFromFile( &cpu, &instructionCount);

    runPipeline(&cpu, instructionCount);


    
    for (int i = 0; i < 64; i++) {
        printf("R%d = %d\n", i, cpu.gprs.R[i]);
    }

    return 0;
}

 

