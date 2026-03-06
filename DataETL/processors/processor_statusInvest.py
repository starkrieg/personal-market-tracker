
from bs4 import BeautifulSoup

import model.standard_data_model as standard_data_model

# To be used with website
# https://statusinvest.com.br/acoes/{TICKET-NAME}

def _process_indicators(soup):

    # extract data on day value
    dayValueElm = soup.find("div", title="Valor atual do ativo").find("strong")
    dayValue = dayValueElm.text.strip()

    # remove R$ char
    dayValue = dayValue.replace('R$', '').strip()

    # statusInvest has number formats in d.ddd,dd
    # so normalize to dddd.dd so it can act as float in python
    dayValue = dayValue.replace('.', '').replace(',', '.')

    # if invalid value, store None
    if dayValue == '-':
        dayValue = None
    ###
    outputData = standard_data_model.StandardizedData()

    # store ticket day value first
    outputData.valuationData.day_value = float(dayValue) if dayValue else None

    # start indicators data extraction
    parentIndicators = soup.find("div", id="indicators-section").find_all(class_="indicators", limit=5)
    cells = []

    for indicator in parentIndicators:
        cells.extend(indicator.find_all(class_=["title", "value"]))
    ###

    

    # cells is a sequential list of title-value
    # so loop with 2 step
    for idx in range(0, len(cells), 2):
        title = cells[idx].text.strip()
        value = cells[idx+1].text.strip()

        # remove % char
        value = value.replace('%', '')

        # statusInvest has number formats in d.ddd,dd
        # so normalize to dddd.dd so it can act as float in python
        value = value.replace('.', '').replace(',', '.')

        # if invalid value, store None
        if value == '-':
            value = None
        ###

        extractedData = {
            "id": title,
            "value": float(value) if value else None
        }

        # print to validate extracted data
        # print(extractedData)
        
        ## bunch of if cases to parse the extracted data to the standardized object
        ## using 'elif' so it gets out of the IF blocks after seting value once
        ## maybe change to a Dict<id, function> to make things simpler?

        ### valuation
        if extractedData['id'] == 'P/L' :
            outputData.valuationData.preco_over_lucro = extractedData['value']
        elif extractedData['id'] == 'PEG Ratio' :
            outputData.valuationData.peg_ratio = extractedData['value']
        elif extractedData['id'] == 'P/SR' :
            outputData.valuationData.p_over_sr = extractedData['value']
        elif extractedData['id'] == 'P/VP' :
            outputData.valuationData.p_over_vp = extractedData['value']
        elif extractedData['id'] == ('D.Y') :
            outputData.valuationData.div_yield = extractedData['value']
        elif extractedData['id'] == 'EV/EBITDA' :
            outputData.valuationData.ev_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'EV/EBIT' :
            outputData.valuationData.ev_over_ebit = extractedData['value']
        elif extractedData['id'] == 'P/EBITDA' :
            outputData.valuationData.p_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'P/EBIT' :
            outputData.valuationData.p_over_ebit = extractedData['value']
        elif extractedData['id'] == 'P/Ativo' :
            outputData.valuationData.p_over_ativo = extractedData['value']
        elif extractedData['id'] == 'P/Cap. Giro' :
            outputData.valuationData.p_over_cap_giro = extractedData['value']
        elif extractedData['id'] == 'P/Ativo Circ. Liq.' :
            outputData.valuationData.p_over_ativo_circ_liq = extractedData['value']
        elif extractedData['id'] == 'VPA' :
            outputData.valuationData.vpa = extractedData['value']
        elif extractedData['id'] == 'LPA' :
            outputData.valuationData.lpa = extractedData['value']

        ### efficiency
        elif extractedData['id'] == 'M. Líquida' :
            outputData.efficiencyData.margem_liq = extractedData['value']
        elif extractedData['id'] == 'M. Bruta' :
            outputData.efficiencyData.margem_bruta = extractedData['value']
        elif extractedData['id'] == 'M. EBIT' :
            outputData.efficiencyData.margem_ebit = extractedData['value']
        elif extractedData['id'] == 'M. EBITDA' :
            outputData.efficiencyData.margem_ebitda = extractedData['value']

        ### profit
        elif extractedData['id'] == 'Giro ativos' :
            outputData.profitData.giro_ativos = extractedData['value']
        elif extractedData['id'] == 'ROE' :
            outputData.profitData.roe = extractedData['value']
        elif extractedData['id'] == 'ROIC' :
            outputData.profitData.roic = extractedData['value']
        elif extractedData['id'] == 'ROA' :
            outputData.profitData.roa = extractedData['value']

        ### debt
        elif extractedData['id'] == 'Dív. líquida/PL' :
            outputData.debtData.div_liq_over_pl = extractedData['value']
        elif extractedData['id'] == 'Dív. líquida/EBITDA' :
            outputData.debtData.div_liq_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'Dív. líquida/EBIT' :
            outputData.debtData.div_liq_over_ebit = extractedData['value']
        # unmapped
        # elif extractedData['id'] == 'DÍVIDA BRUTA / PATRIMÔNIO' :
        #     outputData.debtData. = extractedData['value']
        elif extractedData['id'] == 'PL/Ativos' :
            outputData.debtData.pl_over_ativos = extractedData['value']
        elif extractedData['id'] == 'Passivos/Ativos' :
            outputData.debtData.pass_over_ativos = extractedData['value']
        elif extractedData['id'] == 'Liq. corrente' :
            outputData.debtData.liq_corrente = extractedData['value']

        ### growth
        elif extractedData['id'] == 'CAGR Receitas 5 anos' :
            outputData.growthData.cagr_receitas_5_anos = extractedData['value']
        elif extractedData['id'] == 'CAGR Lucros 5 anos' :
            outputData.growthData.cagr_lucros_5_anos = extractedData['value']

        ### unknown
        else:
            print(f"Error: could not standardize extracted id [{extractedData['id']}]:[{extractedData['value']}] from StatusInvest")
    ###

    return outputData
###

def process(filePath):
    file = open(filePath, "r", encoding="utf8")

    soup = BeautifulSoup(file, "html.parser")
    
    # extract indicators
    outputData: standard_data_model.StandardizedData = _process_indicators(soup)

    # 2026-FEB-12
    # StatusAlpha not freely available
    # So no extraction anymore

    file.close()

    return outputData