/*
 * XAdES4j - A Java library for generation and verification of XAdES signatures.
 * Copyright (C) 2022 Luis Goncalves.
 *
 * XAdES4j is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or any later version.
 *
 * XAdES4j is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with XAdES4j. If not, see <http://www.gnu.org/licenses/>.
 */

package xades4j.utils;

import xades4j.providers.impl.KeyStoreKeyingDataProvider;

import java.util.List;

public final class PtCcSigningCertificateSelector implements KeyStoreKeyingDataProvider.SigningCertificateSelector
{
    @Override
    public Entry selectCertificate(List<Entry> availableCertificates)
    {
        return availableCertificates.stream()
                .filter(it -> it.getAlias().contains("AUTHENTICATION"))
                .findFirst()
                .orElseThrow();
    }
}
