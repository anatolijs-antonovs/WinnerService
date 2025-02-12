package com.app.service;

import com.app.entity.TopWinner;
import com.app.entity.Winner;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "WinnerService", description = "The WinnerService Api")
public interface WinnerService {

    /**
     * Method to handle new winners.
     * @param winner the winner data.
     */
    public void addWin(Winner winner);

    /**
     * Method to get top winners.
     * @return top 10 winners.
     */
    public List<TopWinner> getTopWinners();
}
