package ivann.plugin.ui;

import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.ui.config.BooleanParameter;
import org.gudy.azureus2.plugins.ui.config.Parameter;
import org.gudy.azureus2.plugins.ui.config.ParameterListener;
import org.gudy.azureus2.plugins.ui.config.StringParameter;
import org.gudy.azureus2.plugins.ui.model.BasicPluginConfigModel;

public class ConfigMenu {

	private BasicPluginConfigModel config_page;
	
	private PluginConfig plugin_config;
	
	private boolean stop_enable;
	
	private String fake_ratio_limit;
	
	BooleanParameter enabled_param;
	
	StringParameter fake_share_ratio_text;
	
	/**
	 * ConfigMenu constructor
	 * @param config_page
	 * @param plugin_config
	 */
	public ConfigMenu(BasicPluginConfigModel config_page, PluginConfig plugin_config) {
		this.config_page = config_page;
		this.plugin_config = plugin_config;
		
		setupConfigPage();
	}

	/**
	 * Setup configuration page look and feel
	 */
	private void setupConfigPage() {
		//Set top page information
		config_page.addLabelParameter2("fakeautostop.config.title");
		fake_ratio_limit = plugin_config.getPluginStringParameter("fake share ratio", "1.000");
		stop_enable = plugin_config.getPluginBooleanParameter("fake share limit enable");
		
		//Set action information
		setFakeLimitText();
		setEnableBox();
	}
	
	
	
	/**
	 * Set the enable box and its action
	 */
	private void setEnableBox() {
		enabled_param = config_page.addBooleanParameter2("fake share limit enable", "fakeautostop.config.enable", false);
		enabled_param.addEnabledOnSelection(fake_share_ratio_text);
		
		enabled_param.addListener(new ParameterListener() {
			@Override
			public void parameterChanged(Parameter arg0) {		
				//Check if value inserted is a float if not then set it back to default value
				stop_enable = plugin_config.getPluginBooleanParameter("fake share limit enable");
				System.out.println("Box has changed to " + stop_enable);
			}	
		});
	}
	
	/**
	 * Set the fake ratio value text box and listener
	 */
	private void setFakeLimitText() {
		fake_share_ratio_text = config_page.addStringParameter2("fake share ratio", "fakeautostop.config.max_ratio", fake_ratio_limit);
		
		
		fake_share_ratio_text.addListener(new ParameterListener() {
			@Override
			public void parameterChanged(Parameter arg0) {		
				
				//Check if value inserted is a float if not then set it back to default value
				System.out.println("Value has changed to " + fake_share_ratio_text.getValue());
				
				try {
					Float fake_share_ratio_float = new Float(fake_share_ratio_text.getValue());
				} catch (NumberFormatException e) {
					System.out.println("Not a float");
					
					plugin_config.setPluginParameter("fake share ratio", fake_ratio_limit);
					fake_share_ratio_text.setValue(fake_ratio_limit);
				}
				
				fake_ratio_limit = plugin_config.getPluginStringParameter("fake share ratio");
			}	
		});
	}
	
	/**
	 * Get if stopping is enabled
	 * @return
	 */
	public boolean getLimitEnable() {
		return stop_enable;
	}
	
	/**
	 * Get the fake ratio limit
	 * @return
	 */
	public String getFakeRatioLimit() {
		return fake_ratio_limit;
	}

}
