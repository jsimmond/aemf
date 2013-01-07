Android Event Monitoring Framework (AEMF)
=========================================

Version 1.0
:::::::::::::::::::


The AEMF Framework is an application development Framework for Android devices that allow it to monitoring 
behavior applications according to the actions produced by them.

AEMF is based from the Automata Theory and implements a behavioral conformance checking approach, where 
the developer describe what behavior he is expecting
from the monitored application. Those behavior descriptions are defined as a finite automaton declared on XML files
(each automata is defined in his own file).

As in the Automata Theory, each automata needs his own alphabet to work, and the developer must provide it describing 
other XML file. 

Each arc from automaton represents an action to be performed by the application. If one automata performs that action,
then the automata changes from the currect state to the next given by the action performed.

Actually, the application actions are captured from the /dev/log/main log file and it is readed in real time by 
the Framework. For each action readed, the framework parse it to a symbol (described in the alphabet file) and then 
each automata process it symbol and change their states.

The automaton, needs 3 types of states:
 - Initial State: Where the application start
 - Intermediate State: States between the initial and the final(s) states
 - Final State: Where the monitoring finish

Each Final State from the automata describe a non expecting behavior state (such a send SMS from a game without user permission).



For each action readed, 
