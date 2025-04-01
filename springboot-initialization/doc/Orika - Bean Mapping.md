# Mapping with Orika
Orika is a java BEan mapping framework that recursively copies data from one object to another.

Orika uses byte code generation to create fast mappers with minimal overhead, making it much faster than other reflection based mappers.

## Simple Example
The basic cornerstone of the mapping framework is the MapperFactory class, This is the class we will use to configure mappings and obtain the MappingFacade instance which performs the actual mapping work.
We create a MapperFactory object like:
`MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();`
