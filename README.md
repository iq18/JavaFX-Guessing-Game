Object Orientated Programming-Year02-Semester02-Lab01

Develop a JavaFX application. 
The first pane is a guessing game. Guess a number 1 to 100. After each guess you are told whether you are too high or too low or if you have guessed the correct number. 
You are allowed 6 attempts. The application has a reset button, a guess button, a quit button and uses a random number generator. This game wins a 4 * prize.

The second pane simulates the national lottery user can guess numbers and they are compared with a set of pre-generated random numbers. 
The user gets a prize for getting 4 or more numbers correct. A 4 * prize for 4 numbers, 5* for 5 numbers, etc..
The user is allowed any number of attempts at guessing the lottery numbers .This tab is called lotto cure.

The third pane has a button which is used to set the prize selection using an observable list (or other) of items populated from a text file the user can 
select one of the options this value is used as a key to a hash map which stores a prize value...(It can be text or value)
The strings can be anything apple banana pear, these are used to lookup the Map to get the appropriate prize. 
Example File contents:
E.g.   Prize                  Stars(4/5/6)   Name
       1000                	  4              Apple
       10000                  5              Grape
       Trip to Donegal        4              Pear    
       Trip to Caribbean      4              Banana                
            
List for a person who won a 4 prize. Users only see the strings to choose from. E.g. apple, pear Banana. The prize is retrieved from the Map. 
Use a pop-up to announce the prize.
The user is only allowed use the third tab if he/she has won a game in either tab 1 or 2. 
