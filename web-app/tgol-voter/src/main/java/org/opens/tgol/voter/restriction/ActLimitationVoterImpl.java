/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.voter.restriction;

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ActLimitationVoterImpl implements RestrictionVoter {

    private ActDataService actDataService;

    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }

    @Override
    public String checkRestriction(
            Contract contract, 
            OptionElement optionElement, 
            String clientIp, 
            ScopeEnum scope) {
        
        int nbOfAct = Integer.valueOf(optionElement.getValue());
        if (nbOfAct <= actDataService.getNumberOfAct(contract)) {
            return TgolKeyStore.ACT_QUOTA_EXCEEDED;
        }
        return TgolKeyStore.ACT_ALLOWED;
    }

}
