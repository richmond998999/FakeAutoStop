package ivann.plugin.ui;

import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.ui.model.BasicPluginConfigModel;

public class ConfigMenu {

	private BasicPluginConfigModel config_page;
	
	private PluginConfig plugin_config;
	
	
	public ConfigMenu(BasicPluginConfigModel config_page, PluginConfig plugin_config) {
		this.config_page = config_page;
		this.plugin_config = plugin_config;
	}

}
