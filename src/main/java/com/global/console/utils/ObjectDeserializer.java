package com.global.console.utils;

import org.modelmapper.ModelMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectDeserializer.
 */
public class ObjectDeserializer {

	/** The model mapper. */
	private static ModelMapper modelMapper;

	/**
	 * Gets the model mapper instance.
	 *
	 * @return the model mapper instance
	 */
	private static ModelMapper getModelMapperInstance() {
		if (modelMapper == null) {
			synchronized (ObjectDeserializer.class) {
				if (modelMapper == null) {
					modelMapper = new ModelMapper();
				}
			}
		}
		return modelMapper;
	}

	/**
	 * Gets the object mapped.
	 *
	 * @param <T>
	 *            the generic type
	 * @param inputObject
	 *            the input object
	 * @param outputObject
	 *            the output object
	 * @return the object mapped
	 */
	public static <T> T getObjectMapped(Object inputObject, Class<T> outputObject) {
		return getModelMapperInstance().map(inputObject, outputObject);
	}
}
