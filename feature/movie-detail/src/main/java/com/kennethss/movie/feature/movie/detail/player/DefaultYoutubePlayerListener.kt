package com.kennethss.movie.feature.movie.detail.player

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener

class DefaultYoutubePlayerListener(
    private val videoId: String
) : YouTubePlayerListener {
    override fun onApiChange(youTubePlayer: YouTubePlayer) {}

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {}

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {}

    override fun onPlaybackQualityChange(
        youTubePlayer: YouTubePlayer,
        playbackQuality: PlayerConstants.PlaybackQuality
    ) {
    }

    override fun onPlaybackRateChange(
        youTubePlayer: YouTubePlayer,
        playbackRate: PlayerConstants.PlaybackRate
    ) {
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        youTubePlayer.loadVideo(videoId, 0.0f)
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) { }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) { }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) { }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) { }
}