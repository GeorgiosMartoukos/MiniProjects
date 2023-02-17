# MiniProjects

<h2>CombinationsOfSix</h2> <br>
This is a Java program that generates all possible combinations of six numbers from a list of integers stored in a text file, and writes the combinations that meet certain conditions to another text file. The program checks whether the combination meets the following conditions:

There are no more than four even numbers and four odd numbers.
No more than three numbers have the same last digit.
No more than two numbers are contiguous.
Usage <br>
To use this program, simply run the main method in the CombinationsOfSix class. You will be prompted to start the program by pressing any key (except "q" or "Q"). Once the program is running, it will read the list of integers from a file called "numbers.txt" located at "D:/tmp/combinationOfSixProject/numbers.txt", and it will write the combinations that meet the conditions to a file called "combinations.txt" located at "D:/tmp/combinationOfSixProject/combinations.txt".

Note: If you want to use a different input file, you will need to modify the path to the file in the code.

<h2>TheaterBookingApp</h2> <br>

The given Java code is a program that simulates a theater booking application. Here is how the program works:

The program starts by initializing a 2D boolean array of size 12x31 representing the availability of each seat in the theater. <br>
The program displays a menu of three options: book a seat, cancel a booking, and view available seats. <br>
The user is prompted to input their choice by entering a number or the letter "q". <br>
If the user chooses to book a seat, they are prompted to enter a row (a capital letter between A and L) and a column (an integer between 0 and 30) for the seat they want to book. If the seat is available, it is booked and the program prints a message saying that the booking was successful. If the seat is not available, the program prints a message saying that the seat is not available. <br>
If the user chooses to cancel a booking, they are prompted to enter a row and column for the seat they want to cancel. If the seat is booked, it is canceled and the program prints a message saying that the cancellation was successful. If the seat is not booked, the program prints a message saying that the seat is not booked. <br>
If the user chooses to view available seats, the program prints a table showing the availability of each seat in the theater. <br>
The program continues to display the menu and prompt the user for their choice until the user chooses to quit by entering the letter "q". <br>
Any input errors are caught and logged to a log file located at "D:/tmp/log-theater.txt". <br>
The program is structured as a single class called "TheaterBookingApp" and has several static methods for displaying the menu, getting user input, handling user choices, <br> and performing seat bookings and cancellations. The program makes use of exception handling to catch input errors and logs these errors to a log file.
