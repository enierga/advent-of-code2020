class TreeCounter 
  # use command line to pass in file as argument
  FILENAME = ARGV[0]
  @trees = 0
  @open = 0
  @rowIndex = 2
  @currentRow = 0

  File.readlines(FILENAME).each do |line|
    @row = line.split('')
    if @currentRow != 0
      if @rowIndex > 30
        @rowIndex = @rowIndex % 30
      end

      if @row[@rowIndex] == '#'
        @row[@rowIndex] = 'X'
        @trees += 1
      else
        @row[@rowIndex] = 'O'
        @open += 1
      end
      @rowIndex += 3
    end
    @currentRow += 1

    puts "#{@row}"
    # puts "Row:#{@currentRow} Column:#{@rowIndex} Spot:#{@row[@rowIndex]}"
  end
  puts "Number of trees:#{@trees} Number of open spaces:#{@open}"
end
