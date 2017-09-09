package core.function.administrator;

import edu.csus.ecs.pc2.api.ServerConnection;

/**
 * 添加编程语言
 * @author unclesky4  09/09/2017
 *
 */
public class AddLanguage {
	
	ServerConnection serverConnection = null;
	
	public AddLanguage(ServerConnection serverConnection) {
		this.serverConnection = serverConnection;
	}
	
	public void addLanguages() {
		serverConnection.addLanguage("Java");
		serverConnection.addLanguage("GNU C++ (Unix / Windows)");
		serverConnection.addLanguage("GNU C (Unix / Windows)");
		serverConnection.addLanguage("PHP");
		serverConnection.addLanguage("Microsoft C++");
		serverConnection.addLanguage("Python");
		
		/*serverConnection.addLanguage("Java", "javac {:mainfile}", "java {:basename}", true, "{:basename}.class");
		serverConnection.addLanguage("GNU C", "gcc -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", true, "{:basename}.exe");
		serverConnection.addLanguage("GNU C++", "g++ -lm -o {:basename}.exe {:mainfile}", "./{:basename}.exe", true, "{:basename}.exe");
		serverConnection.addLanguage("PHP", "php -l {:mainfile}", "php {:mainfile}", true, "");
		serverConnection.addLanguage("Python", "python -m py_compile {:mainfile}", "python {:mainfile}", true, "");
		*/
	}

}
