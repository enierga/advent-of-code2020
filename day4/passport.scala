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
      // println(line+":"+file(line))
      if(file(line) == "" || line == (file.length-1)) {
        if(checkPassport(passport)(0)) {
          validPassports(0) += 1
        }
        if(checkPassport(passport)(1)) {
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
    // println("Passport:"+passport)

    iyr0 = passport contains "iyr:"
    iyr1 = checkIyr(passport)
    // println("IYR Result:"+checkIyr(passport))

    byr0 = passport contains "byr:"
    byr1 = checkByr(passport)
    // println("BYR Result:"+checkByr(passport))

    eyr0 = passport contains "eyr:"
    eyr1 = checkEyr(passport)
    // println("EYR Result:"+checkEyr(passport))

    hgt0 = passport contains "hgt:"
    hgt1 = checkHgt(passport)
    // println("HGT Result:"+checkHgt(passport))

    hcl0 = passport contains "hcl:"
    hcl1 = checkHcl(passport)
    // println("HCL Result:"+checkHcl(passport))

    ecl0 = passport contains "ecl:"
    ecl1 = checkEcl(passport)
    // println("ECL Result:"+checkEcl(passport))

    pid0 = passport contains "pid:"
    pid1 = checkPid(passport)
    // println("PID Result:"+checkPid(passport))

    if (iyr1 && byr1 && eyr1 && hgt1 && hcl1 && ecl1 && pid1) {
      println(passport)
      println("IYR Result:"+checkIyr(passport))
      println("BYR Result:"+checkByr(passport))
      println("EYR Result:"+checkEyr(passport))
      println("HGT Result:"+checkHgt(passport))
      println("HCL Result:"+checkHcl(passport))
      println("ECL Result:"+checkEcl(passport))
      println("PID Result:"+checkPid(passport))
    }
    // println("iyr:"+iyr+" byr:"+byr+" eyr:"+eyr+" hgt:"+hgt+" hcl:"+hcl+" ecl:"+ecl+" pid:"+pid+" cid:"+cid)
    // println("Passport Result:"+ (iyr && byr && eyr && hgt && hcl && ecl && pid))
    return Array((iyr0 && byr0 && eyr0 && hgt0 && hcl0 && ecl0 && pid0), (iyr1 && byr1 && eyr1 && hgt1 && hcl1 && ecl1 && pid1))
  }
  def checkIyr(passport: String) : Boolean = {
    val pattern = """iyr:(201\d|2020)""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkByr(passport: String) : Boolean = {
    val pattern = """byr:(19[2-9]\d|200[0-2])""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkEyr(passport: String) : Boolean = {
    val pattern = """eyr:(20(2\d|30))""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkHgt(passport: String) : Boolean = {
    val pattern = """hgt:((1(([5-8]\d)|(9[0-3]))cm)|((59|6\d|7[0-6])in))""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkHcl(passport: String) : Boolean = {
    val pattern = """hcl:(#[0-9a-f]{6})""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkEcl(passport: String) : Boolean = {
    val pattern = """ecl:(amb|blu|brn|gry|grn|hzl|oth)""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
  def checkPid(passport: String) : Boolean = {
    val pattern = """pid:(\d{9})""".r
    // println(pattern.findFirstMatchIn(passport))
    pattern.findFirstMatchIn(passport) match {
      case Some(_) => return true
      case None => return false
    }
  }
}
