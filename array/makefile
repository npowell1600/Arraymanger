all : Array.class ArrayManager.class ArrayClient.class SegmentationViolationException.class OutOfMemoryException.class

clean :
	-rm *.class 2> /dev/null

Array.class : Array.java
	javac Array.java

ArrayManager.class : ArrayManager.java
	javac ArrayManager.java

ArrayClient.class : ArrayClient.java
	javac ArrayClient.java

SegmentationViolationException.class : SegmentationViolationException.java
	javac SegmentationViolationException.java

OutOfMemoryException.class : OutOfMemoryException.java
	javac OutOfMemoryException.java
