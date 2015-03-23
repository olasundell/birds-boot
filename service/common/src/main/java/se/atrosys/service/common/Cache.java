package se.atrosys.service.common;

public enum Cache {
	SOUND_CACHE(Constant.SOUND_CACHE_NAME, 10, 1000);

	private final String cacheName;
	private final int timeToLiveSeconds;
	private final int maxEntriesLocalHeap;

	Cache(String cacheName, int timeToLiveSeconds, int maxEntriesLocalHeap) {
		this.cacheName = cacheName;
		this.timeToLiveSeconds = timeToLiveSeconds;
		this.maxEntriesLocalHeap= maxEntriesLocalHeap;
	}

	public String getCacheName() {
		return cacheName;
	}

	public int getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public int getMaxEntriesLocalHeap() {
		return maxEntriesLocalHeap;
	}

	public static class Constant {
		public static final String SOUND_CACHE_NAME = "sound_cache";
	}
}
