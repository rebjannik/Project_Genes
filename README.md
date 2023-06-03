# Project_DA2023

## DNA project - how to use
Either download the entire source code in one go and run it on prefered IDE or by using the jar file on the command line with specified extra memory and heap size. Creates 4 extra files if correct data is given, a mapping file for expanding the project if contig names will be needed or not, a processed files with valid pairs but not sorted, a degreeDistribution csv file, and a DensityDistribution csv file. All files were created to save memory, space and time when running the code again and again. There will also be two images of a graph. All of this, except for the huge data file can be found in this repository.

### There are about 273187 components in the Graph with a size 3 or larger.

## Work and algorithms
I have been working quite extensively with as small memory taking as possible. In the beginning it was amazing when a little txt file was read fast and the graph was computed, but as soon as the bigger txt files started to come into play it became harder and harder to make it work on my computer. But after resorting to the most basic data types and removing any excess It came up in the 59 million pair processed. I don't know if I had any other real defined algorithm used than the dfs on for the graph traversal. It's been both very interesting and fun to work with this big and imperfect data set, compared to the usual well defined data samples, but it has also been very frustrating realizing that my comuter apparently can't handle it.
