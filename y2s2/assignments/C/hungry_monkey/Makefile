# Setting our compiler
CC = gcc

# Compiler flags
CFLAGS = -g

# Source to target
SRCS = hungry_monkey_printf.c move_monkey.c

TARGET = hungry_monkey

# Default target
all: $(TARGET)

# Rule to create the target
$(TARGET): $(SRCS)
	$(CC) $(CFLAGS) $(SRCS) -o $(TARGET) # Brains behind gcc hmprintf movem -o target

# Clean up build files - not generally needed
# clean:
	# rm -f $(TARGET) 

