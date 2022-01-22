# defining compiler and compiler flag variables
JFLAGS = -g
JC = javac

# clear the default targets for building .class files
# from .java files
.SUFFIXES: .java .class

# target for creating .class files from .java files
.java.class:
	$(JC) $(JFLAGS) $*.java

# macro of the source files
CLASSES = \
	Game.java \
	LoL_StatTracker.java \
	Stats.java

# default make target entry
default: classes

# replacing suffix of CLASSES files
classes: $(CLASSES:.java=.class)

# clean
clean:
	$(RM) *.class