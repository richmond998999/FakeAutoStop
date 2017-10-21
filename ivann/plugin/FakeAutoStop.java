package ivann.plugin;

import org.gudy.azureus2.plugins.Plugin;
import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.PluginException;
import org.gudy.azureus2.plugins.PluginInterface;
import org.gudy.azureus2.plugins.download.Download;
import org.gudy.azureus2.plugins.download.DownloadManager;
import org.gudy.azureus2.plugins.ui.model.BasicPluginConfigModel;
import org.gudy.azureus2.plugins.utils.Utilities;

import ivann.plugin.config.Config;
import ivann.plugin.config.ConfigMenu;
import ivann.plugin.config.FakeRatioListener;

import org.gudy.azureus2.plugins.ui.config.ActionParameter;
import org.gudy.azureus2.plugins.ui.config.BooleanParameter;
import org.gudy.azureus2.plugins.ui.config.IntParameter;
import org.gudy.azureus2.plugins.ui.config.Parameter;
import org.gudy.azureus2.plugins.ui.config.ParameterListener;

public class FakeAutoStop implements Plugin {

		private ConfigMenu config_menu;
	
	@Override
	public void initialize(PluginInterface pluginInterface) throws PluginException {
		
		//Initialize Config Menu
		BasicPluginConfigModel config_page = pluginInterface.getUIManager().createBasicPluginConfigModel("fakeautostop.config.title");
		PluginConfig plugin_config = pluginInterface.getPluginconfig();
		DownloadManager download_manager = pluginInterface.getDownloadManager();
		Utilities utility = pluginInterface.getUtilities();
		Config config = new Config(plugin_config);
		
		config_menu = new ConfigMenu(config_page, plugin_config , config);
		
		
		pluginInterface.getDownloadManager().addListener(new FakeRatioListener(download_manager, plugin_config, utility, config));
	}
}
