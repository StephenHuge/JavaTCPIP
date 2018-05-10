package chapter1.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 列出指定目录下所有文件
 */
public class FileDemo11 {
	public static void main(String[] args) {
		String dir = "d:/WorkEnvironment";
		File f = new File(dir);
		print(f);
	}
	/**
	 * 传入根文件名，并初始化各种变量：
	 * 1. Content root ： 根节点
	 * 2. List<Integer> fileNumOfEachLayer (fnel) ： 用来记录每层文件的数量，
	 *    index表示层数，默认根节点是第0层
	 * 3. int layer ： 表示层数
	 * 
	 * @param file 传入的根文件
	 */
	public static void print(File file) {
		if (file == null) return;

		Content root = new Content(null, file, 0, 1);   // 根结点
		List<Integer> fileNumOfEachLayer = new ArrayList<>();	// 记录每层文件的数量
		List<Integer> fnel = fileNumOfEachLayer;   // 名称缩写
		int layer = 0;          // 当前所在的层数
		int rootCount = 1;		// root层的文件数为1

		fnel.add(layer, rootCount);     // 将当前层数文件的数量记录在list中
		File thisFile = root.getFile();          // 获取根节点的文件
		System.out.println(thisFile.getPath());  // 打印根节点的文件

		print(root, fnel);
	}
	private static void print(Content c, List<Integer> fnel) {
		if (c == null || fnel == null)  return;
		File f = c.getFile();       // 获取当前节点文件
		File[] sonFiles = f.listFiles();   // 获取当前节点文件的子文件数组
		
		printLine(c, fnel, c.getLayer());		// 判断如何打印并打印当前行
	
		// 如果此文件为文件夹且不为空，继续打印下一层
		if (sonFiles != null) {
			fnel.add(c.getLayer() + 1, sonFiles.length);
			for (int i = 0; i < sonFiles.length; i++) {
				Content sonContent = new Content(c, sonFiles[i], c.getLayer() + 1, i);
				print(sonContent, fnel);	// 递归执行
			}
		}
	}
	private static void printLine(Content c, List<Integer> fnel, int layerOfContent) {
		// 递归直到root的下一层
		if (c.getParent() != null) {
			printLine(c.getParent(), fnel, layerOfContent);
		}
		if (c.getLayer() == layerOfContent) { // 如果已经递归返回到当前层，直接打印当前行并返回
			// 判断目录是否为空或者是否是文件
			String title = (c.getFile().list() == null) ? "- " : "+ ";	
			System.out.println(title + c.getFile().getName());		// 打印文件名	
			return;	
		} else {
			// 判断当前文件是否是自己所在层的最后一个文件
			System.out.print((fnel.get(c.getLayer()) - 1 > c.getId()) ? "│  " : "   ");
		}
	}
	static class Content {
		private Content parent;
		private File file;
		private int layer;      // 相对于根目录此文件的层数
		private int id;   // 本层中所有的文件数，此变量是为了判定最后一个文件用的

		public Content(Content parent, File file, int layer, int id) {
			this.parent = parent;
			this.file = file;
			this.layer = layer;
			this.id = id;
		}

		public Content getParent() {
			return parent;
		}

		public void setParent(Content parent) {
			this.parent = parent;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public int getLayer() {
			return layer;
		}

		public void setLayer(int layer) {
			this.layer = layer;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
