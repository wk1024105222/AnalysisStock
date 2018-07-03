package crawler.cases.fund.vo;

/**
 * FundManager entity. @author MyEclipse Persistence Tools
 */

public class FundManager implements java.io.Serializable {

	// Fields

	private String id;

	private String name;

	private String profile;

	// Constructors

	/** default constructor */
	public FundManager() {

	}

	/** full constructor */
	public FundManager(String name, String profile) {

		this.name = name;
		this.profile = profile;
	}

	// Property accessors

	public String getId() {

		return this.id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getProfile() {

		return this.profile;
	}

	public void setProfile(String profile) {

		this.profile = profile;
	}

}
