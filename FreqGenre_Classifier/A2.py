import os
import librosa
import numpy as np
import soundfile as sf
import matplotlib.pyplot as plt
import pandas as pd

def preprocess_audio(input_dir, output_dir, sr=3000, duration=5.0):
    os.makedirs(output_dir, exist_ok=True)
    target_length = int(sr * duration)

    audio_data = []

    for file in os.listdir(input_dir):
        if file.lower().endswith(('.wav', '.au')):
            path = os.path.join(input_dir, file)
            y, _ = librosa.load(path, sr=sr, mono=True)

            # Trim or pad
            if len(y) > target_length:
                y = y[:target_length]
            else:
                y = np.pad(y, (0, target_length - len(y)))

            sf.write(os.path.join(output_dir, file), y, sr)
            audio_data.append((file, y))

    return audio_data, sr

def compute_dft(signal):
    N = len(signal)
    dft_result = []
    for k in range(N):
        real = 0
        imag = 0
        for n in range(N):
            angle = 2 * np.pi * k * n / N
            real += signal[n] * np.cos(angle)
            imag -= signal[n] * np.sin(angle)
        magnitude = np.sqrt(real**2 + imag**2)
        dft_result.append(magnitude)
    return np.array(dft_result)

def get_dominant_frequency(dft_result, sr, N):
    freqs = np.linspace(0, sr, N)
    index = np.argmax(dft_result)
    return freqs[index]

from sklearn.cluster import KMeans

def cluster_frequencies(dominant_freqs, k=3):
    X = np.array(dominant_freqs).reshape(-1, 1)
    kmeans = KMeans(n_clusters=k, random_state=42)
    labels = kmeans.fit_predict(X)
    return labels, kmeans.cluster_centers_


def visualize_results(file_names, dominant_freqs, labels, centers):
    df = pd.DataFrame({
        'File': file_names,
        'Dominant Frequency (Hz)': dominant_freqs,
        'Cluster': labels
    })

    print("\n=== Audio File Details ===")
    print(df)

    # Bar Graph of Cluster Counts
    plt.figure()
    df['Cluster'].value_counts().sort_index().plot(kind='bar')
    plt.title("Audio Files per Cluster")
    plt.xlabel("Cluster")
    plt.ylabel("Count")
    plt.show()

    # Frequency Ranges per Cluster
    plt.figure()
    df.boxplot(column='Dominant Frequency (Hz)', by='Cluster')
    plt.title("Dominant Frequency Ranges by Cluster")
    plt.suptitle('')
    plt.xlabel("Cluster")
    plt.ylabel("Frequency (Hz)")
    plt.show()

    return df
base_input_dir = 'C:/Users/menad/Downloads/Group B-20250513T193416Z-1-001/Group B'
categories = ['Disco', 'Classical', 'HighFreq']
output_base_dir = 'processed_audio'

for category in categories:
    input_dir = os.path.join(base_input_dir, category)
    output_dir = os.path.join(output_base_dir, category)
    
    # Step 1: Load & preprocess
    data, sr = preprocess_audio(input_dir, output_dir)
    
# Step 2–3: DFT and Dominant Frequency
file_names, dom_freqs = [], []
for file, audio in data:
    dft = compute_dft(audio)
    freq = get_dominant_frequency(dft, sr, len(audio))
    file_names.append(file)
    dom_freqs.append(freq)

# Step 4: Clustering
labels, centers = cluster_frequencies(dom_freqs)

# Step 5: Visualization
df = visualize_results(file_names, dom_freqs, labels, centers)
