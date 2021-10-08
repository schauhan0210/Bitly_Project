package resources;

public enum ApiResources {
	
	ShortenLink("/v4/shorten"),
	GetLink("/v4/bitlinks");
	
	private String resource;
	
	ApiResources(String resource){
		this.resource= resource;
	}
	
	public String getResource(){
		return resource;
	}
	
	

	
	

}
