package dev.pedrohb.cowcannon;

import org.mineacademy.fo.plugin.SimplePlugin;

public final class CowCannon extends SimplePlugin {

	/**
	 * Automatically perform login ONCE when the plugin starts.
	 */
	@Override
	protected void onPluginStart() {
	}

	/**
	 * Automatically perform login when the plugin starts and each time it is
	 * reloaded.
	 */
	@Override
	protected void onReloadablesStart() {
		// You can check for necessary plugins and disable loading if they are missing
		// Valid.checkBoolean(HookManager.isVaultLoaded(), "You need to install Vault so
		// that we can work with packets, offline player data, prefixes and groups.");

		// Uncomment to load variables
		// Variable.loadVariables();

		//
		// Add your own plugin parts to load automatically here
		// Please see @AutoRegister for parts you do not have to register manually
		//
	}

	@Override
	protected void onPluginPreReload() {
		// Close your database here if you use one
		// YourDatabase.getInstance().close();
	}

	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in SimplePlugin but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return
	 */
	public static CowCannon getInstance() {
		return (CowCannon) SimplePlugin.getInstance();
	}
}
