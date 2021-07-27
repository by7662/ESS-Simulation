Name: Benson Yan
File: README.txt
Date: 07/19/2021

How to compile:
In terminal, go to the correct directory. In this case, [proj02--> src]. Then execute javac Simulation.java to
compile.

How to run program:
After compiling, execute java Simulation. Followed by required program argument. This can be referred by
Usage: ./project02 popSize [percentHawks] [resourceAmt] [costHawk-Hawk].

Why using Java:
Since Java is an object-oriented language, I personally believe that using Java is easier to design this project.
The Individual class will be the great justification on one of the reasons why I think using Java makes more sense.

Observation about this simulation:
I observed that no matter what number of Hawks there are in the population, it will not affect Dove to stay alive.
Ultimately, Dove will always stay alive. The only time when the program is terminated beside user input "8", is when
there are only one Dove left and as for the Hawks, they are either all dead or there are none.

Effects from the changes in the initial parameters:
Given any size for the population (popSize), the higher percentage of them are Hawks will result the higher chance of
Hawk encounters with Hawk. This will result in a faster pace of Hawk die off. The resource amount and the cost of
Hawk-Hawk encounters will make minimal effects unless they are equals to 0.

What I learned in relation to ESS and how this relates to game theory specifically and Intelligent Systems in general:
To my understanding, ESS is more or less the dominant strategy in the game theory terms. A more official definition
of ESS is a strategy that is impermeable when adopted by a population in adaptation to a specific environment, that is
to say it cannot be displaced by an alternative strategy which may be novel or initially rare. Specifically to this
project, the Simulation shows the a few patterns in terms of game theory. For example, when the number of Doves is the
majority of the population, Hawks become Dominated individual as this simulation has less possibilities for Hawks to
encounter Hawks which will easily boost up Hawks' resources amount. Similarly, when the number of Hawks takes the
majority of the population, Doves will become the dominated individual since it has a higher chance of Hawks killing
Hawks and the remaining Doves will split the resources. Intelligent System refers to different software tools that
enable decision makers to draw on the knowledge and decision processes of experts in making decisions. 


