package com.leetcode.y2022.m04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Apr20 {
	/**
		388. 文件的最长绝对路径
		假设有一个同时存储文件和目录的文件系统。下图展示了文件系统的一个示例：
		
		这里将 dir 作为根目录中的唯一目录。dir 包含两个子目录 subdir1 和 subdir2 。subdir1 包含文件 file1.ext 和子目录 subsubdir1；subdir2 包含子目录 subsubdir2，该子目录下包含文件 file2.ext 。
		在文本格式中，如下所示(⟶表示制表符)：
		
		dir
		⟶ subdir1
		⟶ ⟶ file1.ext
		⟶ ⟶ subsubdir1
		⟶ subdir2
		⟶ ⟶ subsubdir2
		⟶ ⟶ ⟶ file2.ext
		如果是代码表示，上面的文件系统可以写为 "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 。'\n' 和 '\t' 分别是换行符和制表符。
		文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，所有路径用 '/' 连接。
		上面例子中，指向 file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。
		每个目录名由字母、数字和/或空格组成，每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。
		给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。
		
		示例 1：
		输入：input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
		输出：20
		解释：只有一个文件，绝对路径为 "dir/subdir2/file.ext" ，路径长度 20
		
		示例 2：
		输入：input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
		输出：32
		解释：存在两个文件：
		"dir/subdir1/file1.ext" ，路径长度 21
		"dir/subdir2/subsubdir2/file2.ext" ，路径长度 32
		返回 32 ，因为这是最长的路径
		
		示例 3：
		输入：input = "a"
		输出：0
		解释：不存在任何文件
		
		示例 4：
		输入：input = "file1.txt\nfile2.txt\nlongfile.txt"
		输出：12
		解释：根目录下有 3 个文件。
		因为根目录中任何东西的绝对路径只是名称本身，所以答案是 "longfile.txt" ，路径长度为 12
		 
		
		提示：
		1 <= input.length <= 10^4
		input 可能包含小写或大写的英文字母，一个换行符 '\n'，一个制表符 '\t'，一个点 '.'，一个空格 ' '，和数字。
	 */

	/**
	 * 参考
	 * @param input
	 * @return
	 */
    public int lengthLongestPath1(String input) {
        if(input==null||input.length()==0) return 0;
        String[] words=input.split("\n");
        int[] pathLens=new int[words.length+1];//pathLens[i]存放地i层次的最后面的元素的路径长度
        pathLens[0]=-1;//层次最少是1，为了统一dp操作（具体看下面循环体），取pathLens[0]=-1
        int ans=0;
        //自左向右，dp
        for(String word:words){
            int level=word.lastIndexOf('\t')+1+1;//层次计算
            int nameLen=word.length()-(level-1);//计算名字长度
            //word的父文件夹必定目前是level-1层次的最后一个，因此pathLens[level-1]就是父文件夹路径长度
            //这个word必然是目前本层次的最后一个，因此需要刷新pathLens[level],+1是因为要加一个'\'
            pathLens[level]=pathLens[level-1]+1+nameLen;
            //如果是文件，还需要用路径长度刷新ans
            if(word.contains(".")) ans=Math.max(ans,pathLens[level]);
        }
        return ans;
    }
	
	public int lengthLongestPath(String input) {
		int length = 0;
		String[] inputSplitByEnter = input.split("\\n");
		System.out.println(Arrays.toString(inputSplitByEnter));

		String fileName = null;
		String fullFilePath = null;
		List<String> currentFilePath = new ArrayList<String>(inputSplitByEnter.length);
		int lastFilePathLevel = -1;
		int currentFilePathLevel = 0;
		boolean isDirectory = false;
		for (String line : inputSplitByEnter) {
			currentFilePathLevel = getLevel(line);
			fileName = line.replace("\t", "");
			isDirectory = isDirectory(fileName);
			System.out.printf("=====line:%s(level %d)(last level %d)-----", line.replace("\t", "⟶"),
					currentFilePathLevel, lastFilePathLevel);

			if (currentFilePathLevel < lastFilePathLevel) {
				for (int idx = currentFilePath.size() - 1; idx >= currentFilePathLevel; idx--) {
					currentFilePath.remove(idx);
				}
				if (isDirectory) {
					currentFilePath.add(fileName);
					fullFilePath = String.join("/", currentFilePath);
				} else {
					if (currentFilePath.size() > 0) {
						fullFilePath = String.join("/", currentFilePath.subList(0, currentFilePath.size())) + "/"
								+ fileName;
					} else {
						fullFilePath = fileName;
					}
					length = Math.max(length, fullFilePath.length());
				}
			} else if (currentFilePathLevel == lastFilePathLevel) {
				if (isDirectory) {
					if (currentFilePathLevel < currentFilePath.size()) {
						currentFilePath.remove(currentFilePathLevel);
					}
					currentFilePath.add(currentFilePathLevel, fileName);
					fullFilePath = String.join("/", currentFilePath);
				} else {
					if (currentFilePath.size() > 0) {
						fullFilePath = String.join("/", currentFilePath.subList(0, currentFilePath.size() - 1));
						if (fullFilePath == null || ("").equals(fullFilePath)) {
							fullFilePath = fileName;
						} else {
							fullFilePath = fullFilePath + "/" + fileName;
						}
					} else {
						fullFilePath = fileName;
					}
					length = Math.max(length, fullFilePath.length());
				}
			} else if (currentFilePathLevel > lastFilePathLevel) {
				if (isDirectory) {
					currentFilePath.add(fileName);
					fullFilePath = String.join("/", currentFilePath);
				} else {
					if (currentFilePath.size() > 0) {
						fullFilePath = String.join("/", currentFilePath) + "/" + fileName;
					} else {
						fullFilePath = fileName;
					}
					length = Math.max(length, fullFilePath.length());
				}
			}

			lastFilePathLevel = currentFilePathLevel;
			System.out.printf("(%s)Full file path=%s\n", isDirectory ? "Folder" : "*File", fullFilePath);
		}

		return length;
	}

	private boolean isDirectory(String fileName) {
		return fileName.indexOf(".") <= 0;
	}

	private int getLevel(String line) {
		Pattern pattern = Pattern.compile("\\t");
		Matcher matcher = null;
		matcher = pattern.matcher(line);
		int level = 0;
//		System.out.printf("line=%s  ", line);
		while (matcher.find()) {
//			System.out.printf("%s ", matcher.group(0));
			level++;
		}
		return level;
	}

//	private int getLevel(String line) {
//		int level = 0;
////		System.out.printf("%s %d\n", line, line.indexOf("\t"));
//		while (line.indexOf("\t") >= 0) {
////			System.out.println(line.substring(line.indexOf("\t") + "\t".length(), line.length()));
//			line = line.substring(line.indexOf("\t") + "\t".length(), line.length());
//			level++;
//		}
//		return level;
//	}

	public static void main(String[] args) {
		Apr20 apr20 = new Apr20();

		String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");

		input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");

		input = "a";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");

		input = "file1.txt\nfile2.txt\nlongfile.txt";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");

		input = "dir\n        file.txt";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");
		
		input = "skd\n\talskjv\n\t\tlskjf\n\t\t\tklsj.slkj\n\t\tsdlfkj.sdlkjf\n\t\tslkdjf.sdfkj\n\tsldkjf\n\t\tlskdjf\n\t\t\tslkdjf.sldkjf\n\t\t\tslkjf\n\t\t\tsfdklj\n\t\t\tlskjdflk.sdkflj\n\t\t\tsdlkjfl\n\t\t\t\tlskdjf\n\t\t\t\t\tlskdjf.sdlkfj\n\t\t\t\t\tlsdkjf\n\t\t\t\t\t\tsldkfjl.sdlfkj\n\t\t\t\tsldfjlkjd\n\t\t\tsdlfjlk\n\t\t\tlsdkjf\n\t\tlsdkjfl\n\tskdjfl\n\t\tsladkfjlj\n\t\tlskjdflkjsdlfjsldjfljslkjlkjslkjslfjlskjgldfjlkfdjbljdbkjdlkjkasljfklasjdfkljaklwejrkljewkljfslkjflksjfvsafjlgjfljgklsdf.a";
		System.out.printf("lengthLongestPath=%d\n", apr20.lengthLongestPath(input));
		System.out.println("---------------------------------------------------------------------------------------");

	}
}
