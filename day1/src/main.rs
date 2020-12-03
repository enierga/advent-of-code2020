use std::env;
use std::fs;
use std::io::{self, BufRead, BufReader, Error, ErrorKind};

fn main() -> io::Result<()> {
  let args: Vec<String> = env::args().collect();
  let filename = &args[1];
  let mut nums = read(filename).expect("Trouble with reading file");
  nums.sort();

  // find 2 numbers
  find2_ints(&nums);

  // find 3 numbers
  find3_ints(&nums);

  Ok(())
}

fn read(filename: &str) -> Result<Vec<i64>, Error> {
  let file = fs::File::open(filename).expect("File not found");
  let reader = BufReader::new(file);
  let nums = reader
    .lines()
    .map(|line| line.and_then(|v| v.parse().map_err(|e| Error::new(ErrorKind::InvalidData, e))))
    .collect();
  nums
}

fn find2_ints(nums: &Vec<i64>) {
  let mut i = 0;
  let mut j = nums.len() - 1;
  while i < j {
    if nums[i] + nums[j] == 2020 {
      println!(
        "2 Integers that add to 2020:\nN1={} N2={}\nN1*N2={}",
        nums[i],
        nums[j],
        nums[i] * nums[j]
      );
      break;
    }
    if nums[i] + nums[j] > 2020 {
      j -= 1;
    }
    if nums[i] + nums[j] < 2020 {
      i += 1;
    }
  }
}

fn find3_ints(nums: &Vec<i64>) {
  let mut i = 0;
  while i < nums.len() {
    let mut j = i + 1;
    let mut k = nums.len() - 1;
    while j < k {
      if nums[i] + nums[j] + nums[k] == 2020 {
        break;
      }
      if nums[i] + nums[j] + nums[k] > 2020 {
        k -= 1;
      }
      if nums[i] + nums[j] + nums[k] < 2020 {
        j += 1;
      }
    }
    if nums[i] + nums[j] + nums[k] == 2020 {
      println!(
        "3 Integers that add to 2020:\nN1={} N2={} N3={}\nN1*N2*N3={}",
        nums[i],
        nums[j],
        nums[k],
        nums[i] * nums[j] * nums[k]
      );
      break;
    }
    i += 1;
  }
}
