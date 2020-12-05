class TreeCounter 
  FILENAME = ARGV[0]
  
  def bruh (x, y)
    @trees = 0
    @rowIndex = 0
    @currentRow = 0

    File.readlines(FILENAME).each do |line|
      @row = line.split('')
      
      # check here for doing correct number of steps down
      if @currentRow % y == 0
        if @rowIndex > 30
          # subtract 1 for 0 indexing
          @rowIndex = @rowIndex % 30 - 1
        end
        if @row[@rowIndex] == '#'
          @row[@rowIndex] = 'X'
          @trees += 1
        else
          @row[@rowIndex] = 'O'
        end
        @rowIndex += x
      end
      @currentRow += 1
    end
    @trees
  end

end

# separated here for debugging
tc = TreeCounter.new
r1d1 = tc.bruh(1,1)
r3d1 = tc.bruh(3,1)
r5d1 = tc.bruh(5,1)
r7d1 = tc.bruh(7,1)
r1d2 = tc.bruh(1, 2)
puts "r1d1. Number of trees: #{r1d1}"
puts "r3d1. Number of trees: #{r3d1}"
puts "r5d1. Number of trees: #{r5d1}"
puts "r7d1. Number of trees: #{r7d1}"
puts "r1d2. Number of trees: #{r1d2}"
puts "Product = #{r1d1*r3d1*r5d1*r7d1*r1d2}"