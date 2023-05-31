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
