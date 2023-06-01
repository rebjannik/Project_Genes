import matplotlib.pyplot as plt

data = []

with open("DensityDistribution.txt", "r") as file:
    for line in file:
        values = line.strip().split(",")
        
        for elements in values[:-1]:
            data.append(float(elements))


#Size of the figure
plt.figure(figsize=(10,10))

# Create a histogram with origin at (0, 0)
plt.hist(data, bins=len(data), align='left', edgecolor='black')

# Set the origin at (0, 0)
plt.xlim(0, max(data)+0.1)

# Adds labels
plt.xlabel("Density")
plt.ylabel("Frequency")

# Saves the histogram
plt.savefig("DensityDistribution.png")