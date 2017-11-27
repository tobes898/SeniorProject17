# SeniorProject17
Welcome to the flagship page for the InSpace compiler I built as my senior project! 

# Installation
In its current state, the compiler is built to run on linux OS's only (Other OS support is hopefully coming in the future. Stay tuned!)

# Installing Linux
If you don't have linux on your machine, you can either use a VM or make a linux partition.

# Installing a VM
1) Download the Ubuntu OS from here: https://www.ubuntu.com/download/desktop
2) Download the VM VirtualBox: https://www.virtualbox.org/wiki/Downloads
3) Run the Unbuntu setup when opening virtualbox program
 //Add more to this
 
# Installing the clang and nasm
1) Right click on the desktop, and press open terminal
2) Now that you are on the terminal screen there are two commands that need to be run
3) 1st command: sudo apt install nasm //This will install nasm to linux
4) 2nd command: sudo apt install clang //This will install clang to linux
Note: press enter or enter y if prompted to do so
5) Exit the terminal and then re-open it like in step 1
Nasm and clang should now be installed onto your OS!

# Installing the compiler
1) Make a folder on the desktop called inspace
2) Inside is where you will put your source file, the compiler executable, and where the assembly will be located

# Running the compiler
1) Make sure the terminal is pathed to the folder that you created (Note: if it is not, just use the command: cd Desktop/{your folder name})
2) Run the java executable
3) Commands to run after this:
Cmd 1: nasm -f elf64 source.asm
Cmd 2: clang -o source source.o
Cmd 3: ./source

Your code should now run and any output should show up on the terminal

