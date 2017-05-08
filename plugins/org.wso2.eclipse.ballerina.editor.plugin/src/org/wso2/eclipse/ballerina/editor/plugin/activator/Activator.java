package org.wso2.eclipse.ballerina.editor.plugin.activator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.wso2.eclipse.ballerina.editor.plugin.editors.BalerinaLanguage;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.wso2.eclipse.ballerina.editor.plugin.editors.BALEditor"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		String[] keywords = new String[] { "native", "fnction", "service", "resource", "import", "action", "worker",
				"connector", "return" };

		String[] httpProposals = new String[] { "getMethod", "getRequestURL", "getStatusCode", "convertToResponse",
				"getContentLength", "setContentLength", "setReasonPhrase", "setStatusCode" };

		String[] utilsProposals = new String[] { "base64ToBase16Encode", "base64decode", "base64encode", "getHash" };

		URL configPath = Platform.getConfigurationLocation().getURL();
		String balPreferencesPath = configPath.getPath() + "ballerina.properties";

		Properties prop = null;
		try {
			File file = new File(balPreferencesPath);
			if (file.exists()) {
				// do nothing
			} else {
				PrintWriter writer = new PrintWriter(balPreferencesPath, "UTF-8");
				writer.println("keywords=" + Arrays.toString(keywords));
				writer.println("http=" + Arrays.toString(httpProposals));
				writer.println("utils=" + Arrays.toString(utilsProposals));
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		prop = readAndLoadProperties(balPreferencesPath, prop);
		BalerinaLanguage.getInstance().init(prop);
		plugin = this;
	}

	private Properties readAndLoadProperties(String balPreferencesPath, Properties prop) throws IOException {
		FileInputStream inputStream = null;
		try {
			prop = new Properties();
			inputStream = new FileInputStream(balPreferencesPath);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return prop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 *
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
