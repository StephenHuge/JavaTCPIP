package chapter1.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * �г�ָ��Ŀ¼�������ļ�
 */
public class FileDemo11 {
	
	private static final String DOT = ".";
	
	public static void main(String[] args) {
		String dir = "d:/WorkEnvironment";
		String[] ignoredFolders = {"bin", "lib", ".gradle", "example", "webapps", "work"};
		String[] ignoredSuffixes = {"log", "html", "txt", "jsp", "class", "java"};
		File f = new File(dir);
		print(f, ignoredFolders, ignoredSuffixes);
	}
	/**
	 * ������ļ���������ʼ�����ֱ�����
	 * 1. Content root �� ���ڵ�
	 * 2. List<Integer> fileNumOfEachLayer (fnel) �� ������¼ÿ���ļ���������
	 *    index��ʾ������Ĭ�ϸ��ڵ��ǵ�0��
	 * 3. int layer �� ��ʾ����
	 * 
	 * @param file ����ĸ��ļ�
	 * @param ignoredFolders  ʡ�Ե��ļ������ƻ���ǰ׺
	 * @param ignoredSuffixes ʡ�Ե��ļ����ͺ�׺
	 */
	public static void print(File file, String[] ignoredFolders, String[] ignoredSuffixes) {
		if (file == null) return;

		Content root = new Content(null, file, 0, 1);   // �����
		List<Integer> fileNumOfEachLayer = new ArrayList<>();	// ��¼ÿ���ļ�������
		List<Integer> fnel = fileNumOfEachLayer;   // ������д
		int layer = 0;          // ��ǰ���ڵĲ���
		int rootCount = 1;		// root����ļ���Ϊ1

		fnel.add(layer, rootCount);     // ����ǰ�����ļ���������¼��list��
		File thisFile = root.getFile();          // ��ȡ���ڵ���ļ�
		System.out.println(thisFile.getPath());  // ��ӡ���ڵ���ļ�

		print(root, fnel, ignoredFolders, ignoredSuffixes);
	}
	private static void print(Content c, List<Integer> fnel, String[] ignoredFolders, String[] ignoredSuffixes) {
		if (c == null || fnel == null)  return;
		File f = c.getFile();       // ��ȡ��ǰ�ڵ��ļ�
    	// ��ȡ��ǰ�ڵ��ļ������ļ����飬��list��Ϊ��
		File[] sonFiles = f.listFiles();
		
		// ʡ���ļ��л���ǰ׺���
		for (String ignoredFolder : ignoredFolders) {
				if ((f.getName().equals(ignoredFolder)) 
						|| (f.getName().startsWith(ignoredFolder))) {
					return;
				}
		}
		// ʡ���ļ����ͼ��
		for (String ignoredSuffix : ignoredSuffixes) {
			if (f.getName().endsWith(DOT + ignoredSuffix)) {
				return;
			}
		}
		printLine(c, fnel, c.getLayer());		// �ж���δ�ӡ����ӡ��ǰ��
		// ������ļ�Ϊ�ļ����Ҳ�Ϊ�գ�������ӡ��һ��
		if (sonFiles != null) {
			fnel.add(c.getLayer() + 1, sonFiles.length);
			for (int i = 0; i < sonFiles.length; i++) {
				Content sonContent = new Content(c, sonFiles[i], c.getLayer() + 1, i);
				print(sonContent, fnel, ignoredFolders, ignoredSuffixes);	// �ݹ�ִ��
			}
		}
	}
	private static void printLine(Content c, List<Integer> fnel, int layerOfContent) {
		// �ݹ�ֱ��root����һ��
		if (c.getParent() != null) {
			printLine(c.getParent(), fnel, layerOfContent);
		}
		if (c.getLayer() == layerOfContent) { // ����Ѿ��ݹ鷵�ص���ǰ�㣬ֱ�Ӵ�ӡ��ǰ�в�����
			// �ж�Ŀ¼�Ƿ�Ϊ�ջ����Ƿ����ļ�
            String title = ((c.getFile().list() == null)
                    || (c.getFile().list().length == 0)) ? "- " : "+ ";
			System.out.println(title + c.getFile().getName());		// ��ӡ�ļ���	
			return;	
		} else {
			// �жϵ�ǰ�ļ��Ƿ����Լ����ڲ�����һ���ļ�
			System.out.print((fnel.get(c.getLayer()) - 1 > c.getId()) ? "��  " : "   ");
		}
	}
	static class Content {
		private Content parent;
		private File file;
		private int layer;      // ����ڸ�Ŀ¼���ļ��Ĳ���
		private int id;   // ���������е��ļ������˱�����Ϊ���ж����һ���ļ��õ�

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
