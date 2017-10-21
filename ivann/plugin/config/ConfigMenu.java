package ivann.plugin.config;

import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.ui.config.ActionParameter;
import org.gudy.azureus2.plugins.ui.config.BooleanParameter;
import org.gudy.azureus2.plugins.ui.config.Parameter;
import org.gudy.azureus2.plugins.ui.config.ParameterListener;
import org.gudy.azureus2.plugins.ui.config.StringParameter;
import org.gudy.azureus2.plugins.ui.model.BasicPluginConfigModel;

public class ConfigMenu {

	private BasicPluginConfigModel config_page;
	
	private PluginConfig plugin_config;
	
	private Config config;
	
	private BooleanParameter enabled_param;
	
	private StringParameter fake_share_ratio_text;
	
	private ActionParameter save_button;
	
	/**
	 * ConfigMenu constructor
	 * @param config_page
	 * @param plugin_config
	 */
	public ConfigMenu(BasicPluginConfigModel config_page, PluginConfig plugin_config, Config config) {
		this.config_page = config_page;
		this.plugin_config = plugin_config;
		this.config = config;
		
		setupConfigPage();
	}

	/**
	 * Setup configuration page look and feel
	 */
	private void setupConfigPage() {
		//Set top page information
		config_page.addLabelParameter2("fakeautostop.config.title");
		
		//Set action information
		setFakeLimitText();
		setSaveButton();
		setEnableBox();
	}
	
	
	
	/**
	 * Set the enable box and its action
	 */
	private void setEnableBox() {
		enabled_param = config_page.addBooleanParameter2("fake share limit enable", "fakeautostop.config.enable", false);
		enabled_param.addEnabledOnSelection(fake_share_ratio_text);
		enabled_param.addEnabledOnSelection(save_button);
		
		enabled_param.addListener(new ParameterListener() {
			@Override
			public void parameterChanged(Parameter arg0) {		
				//Check if value inserted is a float if not then set it back to default value
				config.setEnable(plugin_config.getPluginBooleanParameter("fake share limit enable"));
				System.out.println("Box has changed to " + config.isEnable());
			}	
		});
	}
	
	/**
	 * Set the fake ratio value text box and listener
	 */
	private void setFakeLimitText() {
		String fake_ratio_limit = config.getFake_ratio_limit();
		fake_share_ratio_text = config_page.addStringParameter2("fake share ratio", "fakeautostop.config.max_ratio", fake_ratio_limit);
		
		
		fake_share_ratio_text.addListener(new ParameterListener() {
			@Override
			public void parameterChanged(Parameter arg0) {		
				
			}	
		});
	}
	
	private void setSaveButton() {
		String fake_ratio_limit = config.getFake_ratio_limit();
		save_button = config_page.addActionParameter2("fakeautostop.config.save.button", "fakeautostop.config.save.label");
		
		save_button.addListener(new ParameterListener() {

			@Override
			public void parameterChanged(Parameter arg0) {
				// TODO Auto-generated method stub

				//Check if value inserted is a float if not then set it back to default value
				System.out.println("Value has changed to " + fake_share_ratio_text.getValue());
				
				try {
					new Float(fake_share_ratio_text.getValue());
				} catch (NumberFormatException e) {
					System.out.println("Not a float");
					
					config.setFake_ratio_limit(fake_ratio_limit);
					fake_share_ratio_text.setValue(fake_ratio_limit);
				}
				
				config.setFake_ratio_limit(plugin_config.getPluginStringParameter("fake share ratio"));
			}
			
		});
	}

}
