# Dylan DiGeronimo
# 2/1/19
# I pledge my honor that I have abided by the Stevens Honor System

import sys

# Dictionaries of the possible tags for each level
zero_tags = {
    "HEAD",
    "TRLR",
    "NOTE"
}

zero_special_cases = {
    "INDI",
    "FAM"
}

one_tags = {
    "NAME",
    "SEX",
    "BIRT",
    "DEAT",
    "FAMC",
    "FAMS",
    "MARR",
    "HUSB",
    "WIFE",
    "CHIL",
    "DIV",
}

two_tags = {
    "DATE"
}

# Function to handle printing for level 0 tags, more complex than 1 and 2
def level_0(line):
    # Split the line into a list of words
    words = line.split()
    if words[1] in zero_tags:
        # The tag is valid
        print("<-- 0|" + words[1] + "|Y|" + " ".join(words[2:]))
    elif words[2] in zero_special_cases:
        # The tag is valid and one of the special cases
         print("<-- 0|" + words[2] + "|Y|" + words[1])
    else:
        # The tag is invalid
        print("<-- 0|" + words[1] + "|N|" + " ".join(words[2:]))

# Function to handle printing for level 1 tags
def level_1(line):
    # Split the line into a list of words
    words = line.split()
    # words[1] is the tag
    if words[1] in one_tags:
        # The tag is valid
        print("<-- 1|" + words[1] + "|Y|" + " ".join(words[2:]))
    else:
        # The tag is invalid
        print("<-- 1|" + words[1] + "|N|" + " ".join(words[2:]))

# Function to handle printing for level 2 tags
def level_2(line):
    words = line.split()
    # words[1] is the tag
    if words[1] in two_tags:
        # The tag is valid
        print("<-- 2|" + words[1] + "|Y|" + " ".join(words[2:]))
    else:
        # The tag is invalid
        print("<-- 2|" + words[1] + "|N|" + " ".join(words[2:]))

# Function to handle printing for tags of a level higher than 2
def invalid_level(line):
    words = line.split()
    print("<-- " + words[0] + "|" + words[1] + "|N|" + " ".join(words[2:]))

def main():
    # Give Python version warning
    if sys.version_info[0] < 3:    
        print("WARNING! Running this code with Python 2 will produce incorrect output due to the change in print statement syntax. Please use Python 3.")
        return -1
    # Get name of GEDCOM script
    if len(sys.argv) == 1:
        print("Usage: python [name of GEDCOM file]")
        print("Example: python proj02test.ged")
        return -1
    else: 
        testfile = sys.argv[1]
        # Read in file
        file = open(testfile, "r")
        # Loop through lines
        for line in file:
            # Print "-->" and the line (except for the new line char)
            print("-->", line[:-1])
            # Check id, dispatch to function for each id level
            if line[0] == "0":
                level_0(line)
            elif line[0] == "1":
                level_1(line)
            elif line[0] == "2":
                level_2(line)
            else:
                invalid_level(line)

if __name__ == "__main__":
    main()