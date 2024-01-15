package com.cleverpine.plex.service.interfaces;

public interface ReceiverService {
    public void receiveMovies(String message);

    public void receiveTvSeries(String message);
}
