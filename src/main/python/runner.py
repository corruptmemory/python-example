import sys
from subprocess import call

def main(argv):
    command = [argv[1], "-classpath", "{}:{}".format(argv[2],argv[3]), argv[4]] + argv[5:]
    call(command)

if __name__ == "__main__":
    main(sys.argv)