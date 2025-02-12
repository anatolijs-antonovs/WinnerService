package com.app.service.impl;

import com.app.entity.TopWinner;
import com.app.entity.Winner;
import com.app.repository.TopWinnersRepository;
import com.app.repository.WinnerRepository;
import com.app.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Service for winner stats
 */

@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    private WinnerRepository winnerRepository;
    @Autowired
    private TopWinnersRepository topWinnersRepository;

    private final int topWinnersCount = 10;

    @Override
    public void addWin(Winner winner) {
        winnerRepository.save(winner);

        // let assume we have 10kkk or more winners, in this situation, even with proper indexes, it is bad idea to
        // search in such table. Instead of it - top winners will be calculated and stored in separate table.
        calculateTopWinners(winner);
    }

    @Override
    public List<TopWinner> getTopWinners() {
        List<TopWinner> winners = new ArrayList<>();
        topWinnersRepository.findAll().iterator().forEachRemaining(winners::add);
        return winners;
    }

    /**
     * One person can win many times, method search for such person and add
     * it new top winner
     * @param winner new winner.
     */
    private void calculateTopWinners(Winner winner) {
        Iterator<TopWinner> it = topWinnersRepository.findAll().iterator();
        int winnersCount = 0;
        boolean isInTop = false;
        TopWinner poorestTopWinner = null;

        while (it.hasNext()) {
            TopWinner topWinner = it.next();

            if (winnersCount < topWinnersCount) {

                // case 1: new winner already in top, increasing total win
                if (topWinner.getName().equals(winner.getName())) {
                    topWinner.setAmount(topWinner.getAmount() + winner.getAmount());
                    topWinnersRepository.save(topWinner);
                    isInTop = true;
                }

                // saving poorest winner
                if (poorestTopWinner == null || poorestTopWinner.getAmount() > topWinner.getAmount()) {
                    poorestTopWinner = topWinner;
                }

            }
            winnersCount++;
        }

        if (!isInTop) {
            // case 2: new winner not in winners list and there is places in top
            if (winnersCount < topWinnersCount) {
                TopWinner topWinner = new TopWinner();
                topWinner.setName(winner.getName());
                topWinner.setAmount(winner.getAmount());

                topWinnersRepository.save(topWinner);
            }

            // case 3: new winner have bigger win than poorest winner in the top
            if (poorestTopWinner != null && poorestTopWinner.getAmount() < winner.getAmount()) {
                TopWinner topWinner = new TopWinner();
                topWinner.setName(winner.getName());
                topWinner.setAmount(winner.getAmount());

                topWinnersRepository.delete(poorestTopWinner);
                topWinnersRepository.save(topWinner);
            }
        }
    }
}
