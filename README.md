RMIMultiChat
============

Implementation of a chatroom service using Java RMI

= How to run 

1. cd <path to the top-level of the project>
2. mvn install 
3. cd target/classes
4. rmiregistry
5. [Open a new terminal tab at the same directory]
6. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatRegistryImpl
7. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatroomProvider localhost
8. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatClientImpl localhost
9. [Repeat step 8 to start another client]