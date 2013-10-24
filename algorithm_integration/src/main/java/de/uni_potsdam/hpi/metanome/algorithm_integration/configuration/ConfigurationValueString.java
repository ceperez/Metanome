package de.uni_potsdam.hpi.metanome.algorithm_integration.configuration;

import de.uni_potsdam.hpi.metanome.algorithm_integration.Algorithm;
import de.uni_potsdam.hpi.metanome.algorithm_integration.AlgorithmConfigurationException;

/**
 * Represents string configuration values for {@link Algorithm}s.
 */
public class ConfigurationValueString implements ConfigurationValue {

	protected final String identifier;
	protected final String value;
	
	/**
	 * Constructs a ConfigurationValueString using the specification's identifier and the string value.
	 * 
	 * @param identifier
	 * @param value
	 */
	public ConfigurationValueString(String identifier, String value) {
		this.identifier = identifier;
		this.value = value;
	}
	
	/**
	 * Sets it's own value on the algorithm (second call of double dispatch).
	 * 
	 * @param algorithm
	 * @throws AlgorithmConfigurationException 
	 */
	@Override
	public void triggerSetValue(Algorithm algorithm) throws AlgorithmConfigurationException {
		algorithm.setConfigurationValue(identifier, value);
	}

}
