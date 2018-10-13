# SeniorProject17
Welcome to the flagship page for the InSpace compiler I built as my senior project! Please see the current_version folder for the most update version of the compiler.

# Installation
In its current state, the compiler is built to run on linux OS's only (Other OS support is hopefully coming in the future. Stay tuned!)

# Installing Linux
If you don't have linux on your machine, you can either use a VM or make a linux partition.

# Installing a VM
1) Download the Ubuntu OS from here. Make sure it is the 64 bit version: https://www.ubuntu.com/download/desktop
   - Press the first install link you see.
   - Scroll down through the donations screen. In the bottom left select Not Now, Take Me To Download
   - If download is taking a crazy amount of time to download (hour+), try canceling the download and pressing the download button
   it should fix the problem and it should be well under an hour
2) Download the VM VirtualBox: https://www.virtualbox.org/wiki/Downloads
   - On the website, under VirtualBox 5.2.8 platform packages select whatever hosts matches your OS.
3) Run the VM VirtualBox install
4) Once VirtualBox install is complete, run VirtualBox
5) In the top right corner, press the new button
6) In the name tab, enter a name for your virtual machine
7) In the type tab, select Linux
8) In the version tab, select Ubuntu 64 bit
9) In the memory tab, just press next. The default memory should be more than enough.
10) In the hard disk tab, select Create a Virtual Hard Disk now. Then press create.
11) In the hard disk file type tab, select VDI and press next.
12) In the storage on physical hard disk, select dynamically allocated and press next.
13) In the file location and size tab, press create as 10 gb is more then plenty.
14) Double click on your now new virtual machine
15) On the select start-up disk screen, click on the folder icon
16) Find your ubuntu .iso file, click on it, and press open
17) With your ubuntu .iso file selected, press the start button
    - Ubuntu will now load onto your system, when it is done you should see the ubuntu first start screen.
    If you get a screen that looks corrupt, exit the virtual machine, select shutdown the virtual machine, and then press ok.
    Double click on the machine again, Ubuntu will load up again, and the problem should now be fixed.
18) With Ubuntu loaded press install Ubuntu
19) On the next screen, just press continue
20) Again, on the next screen just press install now
21) When the prompt shows up, press continue
22) Press continue again
23) One last time, press continue
24) Fill out the information requested on this screen. Virtual Box will fill out some for you as you go.
    - For the password, be sure to make it something you can remember. You will need it everytime you use the Virtual Box
25) Once you have filled out the info, press continue. Ubuntu will now install for real this time.
26) Once it is done, press the restart now button
27) When it reloads, press enter.
28) Congratulations, the Virtual Machine with Ubuntu is all setup!

 
# Installing clang and nasm
1) Right click on the desktop, and press open terminal
2) Now that you are on the terminal screen there are two commands that need to be run. Enter your password if prompted when you run the commands. Also if asked, enter Y and press enter.
3) 1st command: sudo apt install nasm //This will install nasm to linux
4) 2nd command: sudo apt install clang //This will install clang to linux
Note: press enter or enter y if prompted to do so
5) Exit the terminal and then re-open it like in step 1
Nasm and clang should now be installed onto your OS!

# Installing java
In order to run the compiler, java needs to be installed this can be accomplished as follows:
1) Open the terminal
2) Run the command: sudo apt-get install default-jre
3) Exit the terminal and then reopon it. java should now be installed. 

# Installing the compiler
1) On this github, navigate to the current_version folder.
2) Inside click on the InSpace Compiler.zip
3) Click on the download button
4) Press ok when the prompt shows up
5) Right click and press extract
6) On the next screen, click on Desktop and press Extract
7) The compiler should now be on your desktop!

# Running the compiler
1) Inside the InSpace Compiler Folder, there is a file called source.txt. Put all code in here and save it.
2) When you want to run it, open the terminal.
3) With the terminal open, enter the command: cd Desktop
4) After this, enter the command: cd "InSpace Compiler"
5) The terminal should now be pointed at the InSpace Compiler folder.
6) If you haven't already, the first command that needs to be run now is: chmod 755 myscript.sh
7) Once the script has been set to be allowed to run, all that is left to run the compiler is the put in the command: ./myscript.sh
8) Congratulations your program should now run!


# Troubleshooting
1) If you do not think something has been installed, you can check using the following command. (name of software) -version. If it is installed then the version information will come up.
2)Be sure that the file with your source code is called source.txt
