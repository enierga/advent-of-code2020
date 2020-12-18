import scala.io.Source
import scala.util.matching.Regex

object passportValidator{
  def main(args: Array[String]) : Unit = {
    val filename = args(0)
    val file = Source.fromFile(filename).getLines().toArray
    var validPassports =  Array(0,0)

    var passport = ""
    for(line <- 0 to (file.length - 1)) {
      passport += " " + file(line)
      if(file(line) == "" || line == (file.length-1)) {
        var result = checkPassport(passport)
        if(result(0)) {
          validPassports(0) += 1
        }
        if(result(1)) {
          validPassports(1) += 1
        }
        passport = ""
      }
    }
    println("Day 1. Number of Valid Passports:" + validPassports(0))
    println("Day 2. Number of Valid Passports:" + validPassports(1))
  }

  def checkPassport(passport: String) : Array[Boolean] = {
    var byr0, iyr0, eyr0, hgt0, hcl0, ecl0, pid0 = false
    var byr1, iyr1, eyr1, hgt1, hcl1, ecl1, pid1 = false
    val passportArr = passport.split(" ")

    iyr0 = passport contains "iyr:"
    if(iyr0) {
      iyr1 = checkField("""iyr:(201\d|2020)""", passportArr)
    }

    byr0 = passport contains "byr:"
    if(byr0) {
      byr1 = checkField("""byr:(19[2-9]\d|200[0-2])""", passportArr)
    }

    eyr0 = passport contains "eyr:"
    if(eyr0) {
      eyr1 = checkField("""eyr:(20(2\d|30))""", passportArr)
    }

    hgt0 = passport contains "hgt:"
    if(hgt0) {
      hgt1 = checkField("""hgt:((1(([5-8]\d)|(9[0-3]))cm)|((59|6\d|7[0-6])in))""", passportArr)
    }

    hcl0 = passport contains "hcl:"
    if(hcl0) {
      hcl1 = checkField("""hcl:(#[0-9a-f]{6})""", passportArr)
    }

    ecl0 = passport contains "ecl:"
    if(ecl0) {
      ecl1 = checkField("""ecl:(amb|blu|brn|gry|grn|hzl|oth)""", passportArr)
    }

    pid0 = passport contains "pid:"
    if(pid0) {
      pid1 = checkField("""pid:(\d{9})""", passportArr)
    }

    return Array((iyr0 && byr0 && eyr0 && hgt0 && hcl0 && ecl0 && pid0), (iyr1 && byr1 && eyr1 && hgt1 && hcl1 && ecl1 && pid1))
  }
  def checkField(pattern: String, passport: Array[String]) : Boolean = {
    val regex = pattern.r
    for(field <- 0 to (passport.length - 1)) {
      if(regex.matches(passport(field))) {
        return true
      }
    }
    return false
  }
}
