RMIMultiChat
============

Implementation of a chatroom service using Java RMI

= Deliverables =
* Demo video: http://www.youtube.com/watch?v=2Vq6zHlgsAY
* Design Document: https://docs.google.com/document/d/1kxepEtKgdSvSTmUe8-dmfdtp7RAvhWXqtF6OnLXp9oM/edit?usp=sharing
* Source code: https://github.com/cvhu/RMIMultiChat

= How to run =
1. cd <path to the top-level of the project>
2. mvn install 
3. cd target/classes
4. rmiregistry
5. [Open a new terminal tab at the same directory]
6. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatRegistryImpl
7. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatroomProvider localhost
8. java -Djava.security.policy=myPolicy edu/utexas/ee382vJulien/ChatClientImpl localhost
9. [Repeat step 8 to start another client]