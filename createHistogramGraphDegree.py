import matplotlib.pyplot as plt
import numpy as np

degree = []
frequency = []

with open("DegreeFrequency.txt", "r") as file:
    for line in file:
        values = line.strip().split(",")
        if len(values) == 2:
            degree.append(float(values[0]))
            frequency.append(float(values[1]))

# Determine the number of bins based on the range of degree values
bins = np.arange=(0,1.1, 0.08)
counts, bins, pathces = plt.hist(degree, bins, aplha=1)

plt.xticks(np.arange(0,1.1,0.1))
plt.yscale('log')

# Size of the figure
plt.xlabel("Degree")
plt.ylabel("Frequency")
plt.title("Degree Distribution")

# Saves the histogram
plt.savefig("degreeDistribution.png", dpi=600)


