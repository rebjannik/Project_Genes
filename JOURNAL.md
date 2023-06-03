# Rebecca's journal

### 2023-05-21

After a long struggle with trying to figure out how to make github and git work on it's own, the simple solution that after 2021 they changed the way you can connect was a god's sent. Now the git flow is finally up and running and I can start working on the first part of the project, the graphs and shell script.

### 2023-05-22

Decided to create two classes, one for nodes and one for the graph in it's own. The idea is that when running the main program that all questions will be answered. Decided to use the SHA-256 algorithm for converting the long names into 32bit sized integer. The next step is going to be to start actually creating the nodes and edges, but that will be saved for tomorrow as I feel done today.

### 2023-05-26

After some discussion with my own father (who works as a cloud architect at KNOWIT), it's been pointed out that it is going to save a lot of extra processing power when rerunning the same documents if you create two smaller files. The file with .map specifies the configs identifier and which integer it maps to, and the file that ends with .modified will contain the edges with their corresponding integer names that fullfill the criteria of overlap lenght of at least a 1000. I realized now that SHA-256 is a bit too much for this specific project, which is why we are simply working with integeres that range from 1 to the number of unique identifiers (63500106). Next goal is to actually start building the graph and the nodes. 

### 2023-05-30

I decided to do an adjancency list to display the edges as that felt like the most natural thing for me, especially when we are going to look into the node degree distribution. This part was a bit rough adn I do see that I have completely forgotten to update, but after a massive migraine I decided to do this tomorrow or the next time I pick up my code. I am going forward try my best to just comment and comment everything I can. I am also thinking about maybe creating a file reading class that handles the writing and the reading of files, but I am not completely sure yet.


### 2023-05-31

I guess it is time to start talking about time complexity. If you have seen my commits, you might have noticed that I accidentally created an O(n^2) algorithm. Not my finest moment. Now it is around linear time, if not actually a bit faster as I am taking advantage of the hashmaps ingrained searching function when using it to set stuff. As the calculator now has been put up and is running smoothly for all our files that is up there, the next step is to do a histogram. I also decided to delete the Decode class as instead now the hashmap called CodeTable has two hashmaps , one from int to string ans the other from string to int. There is also some things commented away that was and will be used for debugging when the time complexity shoots up yet again (as I am sure it will). I have also added two constant variables, yet again for debugging.

**My father (VincentNikkelen) has been invited to the github repository all his commits have only been done during videocalling and will often have an extra comment if it looks more than style changing.**

### 2023-06-01

So everything runs the way it has to, my biggest issue time and memory wise is the fact that I am only using one line for the densitydistribution text file. I also had to admit defeat that no matter how hard I tried I could not make java make me a histogram, so I decided to just bite the bullet, make two loose code python files and then run it like that. Now the python files are dependent on that there is correctly named files, but this was the best way I could figure it out. Tomorrow or this weekend I will probably make a hashmap instead of a big array but at the moment words are blurring on my computer screen and I need to do something else.

### Final Entry (2023-06-02 and -03)

A lot has happened the last two days, and I was supposed to make an entry, but I didn't stop until it was around 1am and I needed sleep. So let's talk about it. My CPU is apparently not very good, and would not have been able to handle the entire datafile no matter what, now thanks to my father's computer (whose ram was more than double than mine, 16 instead of 6), we got it to work. That doesn't mean that we didn't first try to make it run on mine, which we did by cutting of execcs data that we didn't need. Class for the nodes became regular integers, the codeTable disapears if the mapping files has been created. The edges are now kept track of in an arraylist of an arrraylist. Tho even if this technically makes it longer to run certain aspect, it still takes way less memory than the hashmap. This was all basically done yesterday if not today this morning. Most of todays work was on trying to make the histograms work. Are they pretty? No. But I honestly cannot care anymore about them. This is also why I added the data files that was created for the histograms to make it easier for people to run their own graphing techniques and to get it to a more readable standard. Also I have no justifiable reason why I decided for dfs in the graph traversal than the fact that I am the mst comfortable with that algorithm over bfs.
