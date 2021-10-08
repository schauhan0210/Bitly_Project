package resources;

import pojo.ShortenLinkPayload;

public class TestDataBuild {
	
	public ShortenLinkPayload createShortenLinkPayload(String longURL, String domain){
		ShortenLinkPayload sp = new ShortenLinkPayload();
		sp.setLong_url(longURL);
		sp.setDomain(domain);
		return sp;
	}

}
