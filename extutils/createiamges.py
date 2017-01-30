import sys
import random
import io
import time

fileContent = ''
colorsNeeded = 3
typeOfFile = input("Type of file [RGB|CMYK]:")
if typeOfFile == 'CMYK':
	colorsNeeded = 4;
colorScale = input("Color Scale [01|0256]:")
notParsedDimensions = input("Type the dimensions [lines x columns]:")
seconds = (str)(time.time())

arrLinesAndColumns = notParsedDimensions.split(" x ");

lines = (int)(arrLinesAndColumns[0]);
columns = (int)(arrLinesAndColumns[1]);

for lineNo in range(lines):
	for columnNo in range(columns):
		for colorCode in range(colorsNeeded):
			colorNumber = random.randint(0, 255)
			if colorScale == '01':
				colorNumberBin = bin(colorNumber);
				fileContent += (((str)(colorNumberBin))[2:]);
			else:
				fileContent += (str)(colorNumber);
			if colorCode < colorsNeeded - 1:
				fileContent += ','
			else:
				fileContent += ';'
		if columnNo == columns-1:
			fileContent += '\n'

with open(seconds, "w") as file:
	file.write(typeOfFile)
	file.write('\n')
	file.write(colorScale)
	file.write('\n')
	file.write((str)(columns))
	file.write('\n')
	file.write((str)(lines))
	file.write('\n')
	file.write(fileContent)