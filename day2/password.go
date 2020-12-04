package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	filename := os.Args[1]
	var validPasswords1 int
	var validPasswords2 int

	passwords := openFile(filename)

	for _, password := range passwords {
		if checkPassword1(password) {
			validPasswords1++
		}
		if checkPassword2(password) {
			validPasswords2++
		}
	}

	fmt.Println("Part 1. Total Valid Passwords=", validPasswords1)
	fmt.Println("Part 2. Total Valid Passwords=", validPasswords2)

}

func openFile(filename string) []string {
	file, err := os.Open(filename)
	if err != nil {
		fmt.Println("File not found")
	}
	scanner := bufio.NewScanner(file)

	scanner.Split(bufio.ScanLines)
	var passwords []string

	for scanner.Scan() {
		passwords = append(passwords, scanner.Text())
	}

	file.Close()
	return passwords
}

func checkPassword1(password string) bool {
	lowIndex := strings.IndexRune(password, '-')
	highIndex := strings.IndexRune(password, ' ')
	letterIndex := strings.IndexRune(password, ':')
	pwIndex := letterIndex + 1

	low, errl := strconv.Atoi(password[:lowIndex])
	if errl != nil {
		fmt.Println("Low number not found")
	}
	// +1 here to avoid appending '-' to beginning of high num
	high, errh := strconv.Atoi(password[lowIndex+1 : highIndex])
	if errh != nil {
		fmt.Println("High number not found")
	}
	letter := strings.TrimSpace(password[highIndex:letterIndex])

	letterCounter := 0
	for i := pwIndex; i < len(password); i++ {
		if string([]rune(password)[i]) == letter {
			letterCounter++
		}
	}

	if low <= letterCounter && high >= letterCounter {
		return true
	}
	return false
}

func checkPassword2(password string) bool {
	lowIndex := strings.IndexRune(password, '-')
	highIndex := strings.IndexRune(password, ' ')
	letterIndex := strings.IndexRune(password, ':')
	pwIndex := letterIndex + 1

	// -1 here to adjust for 1 index instead of 0 index
	low, errl := strconv.Atoi(password[:lowIndex])
	if errl != nil {
		fmt.Println("Low number not found")
	}
	high, errh := strconv.Atoi(password[lowIndex+1 : highIndex])
	if errh != nil {
		fmt.Println("High number not found")
	}
	letter := strings.TrimSpace(password[highIndex:letterIndex])

	// adjusting for 1 index instead of 0 index
	low--
	high--

	// trimming password of white spaces
	ptrim := strings.TrimSpace(password[pwIndex:])
	pwletter1 := string([]rune(ptrim)[low])
	pwletter2 := string([]rune(ptrim)[high])

	// XOR here
	if (pwletter1 == letter || pwletter2 == letter) && !(pwletter1 == letter && pwletter2 == letter) {
		return true
	}
	return false
}
