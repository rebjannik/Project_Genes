import matplotlib.pyplot as plt

degree = []
frequency = []
with open("DegreeFrequency.txt", "r") as file:
    for line in file:
        values = line.strip().split(",")
        if len(values) == 2:
            degree.append(float(values[0]))
            frequency.append(float(values[1]))

#Size of the figure
plt.figure(figsize=(10,10))

# Create a histogram with origin at (0, 0)
plt.bar(degree, frequency, width=1, align='edge', edgecolor='black')

# Set the origin at (0, 0)
plt.xlim(0, max(degree)+1)
plt.ylim(0, max(frequency)+1)

#adds labels
plt.xlabel("Degree")
plt.ylabel("Frequency")

# Saves the histogram
plt.savefig("degreeDistribution.png")