import matplotlib.pyplot as plt
import numpy as np
from matplotlib import colors

degree = []
frequency = []

print("reading lines")
with open("DegreeFrequency.txt", "r") as file:
    for line in file:
        values = line.strip().split(",")

        if len(values) == 2:
            degree.append(float(values[0]))
            frequency.append(int(values[1]))

# Create an array with repeated values based on the frequency
print("getting bins")
degree_repeated = np.repeat(degree, frequency)
bin_width = 0.5  # Specify the desired bin width for whole numbers
bins = np.arange(0, max(degree) + bin_width, bin_width)


print("making the graph")
counts, _, patches = plt.hist(degree_repeated, bins=bins, alpha=1, edgecolor="black")

print("labels")
plt.xlabel("Degree")
plt.ylabel("Frequency")
plt.title("Degree Distribution")

print("scales")
plt.yscale('log')
plt.xscale('linear')

print("width")
# Adjust the width of each bar to make them thinner
for patch in patches:
    patch.set_width(bin_width)

print("saving")
# Saves the histogram
plt.savefig("degreeDistribution.png", dpi=600)


