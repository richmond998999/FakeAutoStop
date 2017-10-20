package ivann.plugin;

import org.gudy.azureus2.plugins.Plugin;
import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.PluginException;
import org.gudy.azureus2.plugins.PluginInterface;
import org.gudy.azureus2.plugins.download.Download;
import org.gudy.azureus2.plugins.ui.model.BasicPluginConfigModel;

import ivann.plugin.ui.ConfigMenu;

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
		BasicPluginConfigModel config_page = pluginInterface.getUIManager().createBasicPluginConfigModel("fakeauto");
		PluginConfig plugin_config = pluginInterface.getPluginconfig();
		
		config_menu = new ConfigMenu(config_page, plugin_config);
	}
}
