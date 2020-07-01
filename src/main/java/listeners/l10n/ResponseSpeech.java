package listeners.l10n;

interface ResponseSpeech {

	public String buildCardTitle();
	
	public String buildSpeech();
	
	public String buildReprompt();

	public String buildPostSpeechPrompt();

}