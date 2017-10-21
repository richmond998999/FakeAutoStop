package ivann.plugin.config;

import org.gudy.azureus2.plugins.PluginConfig;

public class Config {

	private String fake_ratio_limit;
	
	private boolean enable;
	
	private PluginConfig plugin_config;
	
	public Config(PluginConfig plugin_config) {
		this.plugin_config = plugin_config;
		this.fake_ratio_limit = plugin_config.getPluginStringParameter("fake share ratio", "1.000");
		this.setEnable(plugin_config.getPluginBooleanParameter("fake share limit enable"));
	}

	public String getFake_ratio_limit() {
		return fake_ratio_limit;
	}

	public void setFake_ratio_limit(String fake_ratio_limit) {
		this.fake_ratio_limit = fake_ratio_limit;
		plugin_config.setPluginParameter("fake share ratio", fake_ratio_limit);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
	
}
