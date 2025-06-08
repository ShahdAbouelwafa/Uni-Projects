
import librosa
import librosa.display
import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import correlate, find_peaks
import sounddevice as sd

song1_path=r"C:\Users\shahd\OneDrive\Desktop\DSP_Assign1_[58-5433_58-0758]\song1.wav"
song2_path=r"C:\Users\shahd\OneDrive\Desktop\DSP_Assign1_[58-5433_58-0758]\song2.wav"
drumsample_path=r"C:\Users\shahd\OneDrive\Desktop\DSP_Assign1_[58-5433_58-0758]\drum.wav"

sr=22050 #standard sampling rate
song1, _=librosa.load(song1_path, sr=sr)
song2, _=librosa.load(song2_path, sr=sr)
drum_sample, _=librosa.load(drumsample_path, sr=sr)

def cross_correlation(song,drum_sample):
    correlation=correlate(song,drum_sample,mode='valid')
    peaks, _=find_peaks(correlation,height=np.max(correlation)*0.5)
    return correlation, peaks

corr1, peaks1 =cross_correlation(song1, drum_sample)
corr2, peaks2=cross_correlation(song2, drum_sample)

plt.figure(figsize=(12,5))
plt.subplot(3,1, 1)
plt.plot(drum_sample,label='Drumbeat Sample')
plt.title('Drumbeat Sample Waveform')
plt.legend()

plt.subplot(3,1, 2)
plt.plot(corr1,label='Song 1 Correlation')
plt.scatter(peaks1,corr1[peaks1],color='red',label='Detected Peaks')
plt.title('Cross-Correlation with Drumbeat-Song1')
plt.legend()

plt.subplot(3,1, 3)
plt.plot(corr2,label='Song 2 Correlation')
plt.scatter(peaks2,corr2[peaks2],color='red',label='Detected Peaks')
plt.title('Cross-Correlation with Drumbeat-Song2')
plt.legend()

plt.tight_layout()
plt.show()

#To determine best match
if len(peaks1)>len(peaks2):
    best_match="Song 1"
    best_song=song1
    
else:
    best_match="Song 2"
    best_song=song2
    
print("The best matching song is:",best_match)  

sd.play(best_song,sr)
sd.wait()
  
    
    
    